package com.example.BankApplication.payment;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserService;
import com.sun.source.tree.PackageTree;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AppUserService appUserService;
    private final AccountService accountService;
    public String payment(PaymentRequest request) throws AccountNotFoundException {
        AppUser appUser = appUserService.getCUrrentUser();

        if (accountService.getByNr(request.getAccountNrTo()).isEmpty())
            throw new IllegalStateException("No such account nr!");

        Account accountFrom = accountService.findAccountByAppUser(appUser);

        if (accountFrom.getBalance() < request.getPaymentAmount()){
            throw new IllegalStateException("Not enough money in account!");
        }

        Payment payment = new Payment(
                request.getBeneficiary(),
                request.getBeneficiaryAccountNr(),
                request.getAccountNrTo(),
                request.getReference(),
                request.getPaymentAmount()
        );




        return appUser.getEmail();
    }
}
