package com.example.BankApplication.payment;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserService;
import com.example.BankApplication.transactionHistory.TransactionHistory;
import com.example.BankApplication.transactionHistory.TransactionHistoryRepository;
import com.sun.source.tree.PackageTree;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.ServletComponentScan;
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
    private final TransactionHistoryRepository transactionHistoryRepository;
    public void savePayment(Payment payment){paymentRepository.save(payment);}
    public RedirectView payment(PaymentRequest request) throws AccountNotFoundException {
//        AppUser appUser = appUserService.getCUrrentUser();

        String email = appUserResource.getUsername();
        AppUser appUser = appUserService.findAppUserByUsername(email);

        if (accountService.getByNr(request.getAccountNrFrom()).isEmpty())
            throw new IllegalStateException("No such account nr!");

        if (accountService.getByNr(request.getBeneficiaryAccountNr()).isEmpty()) {
            TransactionHistory transactionHistory = new TransactionHistory(
                    appUser,
                    request.getAccountNrFrom(),
                    "Payment",
                    Double.parseDouble(request.getPaymentAmount()),
                    "failed",
                    "Beneficiary account not found",
                    LocalDateTime.now()
            );
            transactionHistoryRepository.save(transactionHistory);

            throw new IllegalStateException("No such account nr!");
        }

        Account accountFrom = accountService.findAccountByNr(request.getAccountNrFrom());
        Account accountBeneficiary = accountService.findAccountByNr(request.getBeneficiaryAccountNr());

        double money = Double.parseDouble((request.getPaymentAmount()));

        if (accountFrom.getBalance().compareTo(BigDecimal.valueOf(money)) < 0){
            TransactionHistory transactionHistory = new TransactionHistory(
                    appUser,
                    request.getAccountNrFrom(),
                    "Payment",
                    Double.parseDouble(request.getPaymentAmount()),
                    "failed",
                    "not enough funds",
                    LocalDateTime.now()
            );
            transactionHistoryRepository.save(transactionHistory);

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

        TransactionHistory transactionHistory = new TransactionHistory(
                appUser,
                request.getAccountNrFrom(),
                "Payment",
                Double.parseDouble(request.getPaymentAmount()),
                "success",
                "Payment Successful",
                LocalDateTime.now()
        );
        transactionHistoryRepository.save(transactionHistory);

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
