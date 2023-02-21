package com.example.BankApplication.payment;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserService;
import com.example.BankApplication.transactionHistory.TransactionHistory;
import com.example.BankApplication.transactionHistory.TransactionHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AppUserService appUserService;
    private final AccountService accountService;
    private  final AppUserResource appUserResource;
    private final TransactionHistoryService transactionHistoryService;
    public void savePayment(Payment payment){paymentRepository.save(payment);}
    public RedirectView payment(PaymentRequest request) throws AccountNotFoundException {
//        AppUser appUser = appUserService.getCUrrentUser();
        String email = appUserResource.getUsername();
        AppUser appUser = appUserService.findAppUserByUsername(email);

        TransactionHistory transactionHistory = new TransactionHistory(
                appUser,
                request.getAccountNrFrom(),
                "Payment",
                Double.parseDouble(request.getPaymentAmount()),
                LocalDateTime.now()
        );

        if (accountService.getByNr(request.getAccountNrFrom()).isEmpty())
            throw new IllegalStateException("No such account nr!");

        if (accountService.getByNr(request.getBeneficiaryAccountNr()).isEmpty()) {
            transactionHistoryService.setTransactionStatusAndType(transactionHistory, false, "beneficiary account not found");
            throw new IllegalStateException("No such account nr!");
        }

        Account accountFrom = accountService.findAccountByNr(request.getAccountNrFrom());
        Account accountBeneficiary = accountService.findAccountByNr(request.getBeneficiaryAccountNr());

        double money = Double.parseDouble((request.getPaymentAmount()));

        if (accountFrom.getBalance().compareTo(BigDecimal.valueOf(money)) < 0){
            transactionHistoryService.setTransactionStatusAndType(transactionHistory, false, "not enough funds");
            throw new IllegalStateException("Not enough money in account!");
        }

        Payment payment = new Payment(
                request.getBeneficiary(),
                request.getBeneficiaryAccountNr(),
                request.getAccountNrFrom(),
                request.getReference(),
                money,
                appUser,
                LocalDateTime.now()
        );

        accountFrom.setBalance(accountFrom.getBalance().subtract(BigDecimal.valueOf(money)));
        accountBeneficiary.setBalance(accountBeneficiary.getBalance().add(BigDecimal.valueOf(money)));

        accountService.saveAccount(accountFrom);
        accountService.saveAccount(accountBeneficiary);
        savePayment(payment);

        transactionHistoryService.setTransactionStatusAndType(transactionHistory, true, "payment successful");

        return new RedirectView("/dashboard");
    }

    public String showPaymentHistory() {
        String email = appUserResource.getUsername();
        AppUser appUser = appUserService.findAppUserByUsername(email);

        return "OK";
    }

    public List<Payment> findAllPayments(AppUser appUser) {
        return paymentRepository.findAllPayments(appUser);
    }
}
