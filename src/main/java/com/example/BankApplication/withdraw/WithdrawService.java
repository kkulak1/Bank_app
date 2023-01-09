package com.example.BankApplication.withdraw;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WithdrawService {
    private final WithdrawRepository withdrawRepository;
    private final AppUserService appUserService;
    private final AccountService accountService;
    public void saveWithdraw(Withdraw withdraw){
        withdrawRepository.save(withdraw);
    }

    public Optional<Withdraw> getWithdraw(Withdraw withdraw){
        return withdrawRepository.findById(withdraw.getId());
    }
    public String withdraw(WithdrawRequest request) throws AccountNotFoundException {

        AppUser appUser = appUserService.getCUrrentUser();

        if (accountService.getByNr(request.getAccountNR()).isEmpty())
            throw new IllegalStateException("No such account nr!");


        Withdraw withdraw = new Withdraw(
                LocalDateTime.now(),
                appUser,
                request.getAccountNR(),
                request.getAmountOfMoney()
        );

        Account accountFrom = accountService.findAccountByNr(request.getAccountNR());

        if (accountFrom.getBalance() < request.getAmountOfMoney())
            throw new IllegalStateException("Not enough money in account!");

        accountFrom.setBalance(accountFrom.getBalance() - request.getAmountOfMoney());

        accountService.saveAccount(accountFrom);

        saveWithdraw(withdraw);

        return appUser.getEmail() + " " +accountFrom.getNr().toString();
    }
}
