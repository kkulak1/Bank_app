package com.example.BankApplication.payment;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserService;
import com.sun.source.tree.PackageTree;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AppUserService appUserService;
    private final AccountService accountService;
    private  final AppUserResource appUserResource;
    public void savePayment(Payment payment){paymentRepository.save(payment);}
    public RedirectView payment(PaymentRequest request) throws AccountNotFoundException {
//        AppUser appUser = appUserService.getCUrrentUser();

        String email = appUserResource.getUsername();
        AppUser appUser = appUserService.findAppUserByUsername(email);

        if (accountService.getByNr(request.getAccountNrTo()).isEmpty())
            throw new IllegalStateException("No such account nr!");

        if (accountService.getByNr(request.getBeneficiaryAccountNr()).isEmpty())
            throw new IllegalStateException("No such account nr!");

        Account accountFrom = accountService.findAccountByNr(request.getBeneficiaryAccountNr());
        Account accountTo = accountService.findAccountByNr(request.getAccountNrTo());

        if (accountFrom.getBalance() < request.getPaymentAmount()){
            throw new IllegalStateException("Not enough money in account!");
        }

        Payment payment = new Payment(
                request.getBeneficiary(),
                request.getBeneficiaryAccountNr(),
                request.getAccountNrTo(),
                request.getReference(),
                request.getPaymentAmount(),
                appUser,
                LocalDateTime.now()
        );

        accountFrom.setBalance(accountFrom.getBalance() - request.getPaymentAmount());
        accountTo.setBalance(accountTo.getBalance() + request.getPaymentAmount());

        accountService.saveAccount(accountFrom);
        accountService.saveAccount(accountTo);
        savePayment(payment);

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
