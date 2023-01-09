package com.example.BankApplication.deposit;

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
public class DepositService {
    private final DepositRepository depositRepository;
    private final AppUserService appUserService;
    private final AccountService accountService;
    public void saveDeposit(Deposit deposit){
        depositRepository.save(deposit);
    }

    public Optional<Deposit> getDeposit(Deposit deposit){
        return depositRepository.findById(deposit.getId());
    }
    public String deposit(DepositRequest request) throws AccountNotFoundException {


        AppUser appUser = appUserService.getCUrrentUser();

        Account accountFrom = accountService.findAccountByAppUser(appUser);

        if (accountService.getByNr(request.getAccountNR()).isEmpty())
            throw new IllegalStateException("No such account nr!");

        Deposit deposit = new Deposit(
                LocalDateTime.now(),
                appUser,
                request.getAccountNR(),
                request.getAmountOfMoney()
        );

        Account accountTo = accountService.findAccountByNr(request.getAccountNR());

        accountFrom.setBalance(accountFrom.getBalance() - request.getAmountOfMoney());
        accountTo.setBalance(accountTo.getBalance() + request.getAmountOfMoney());

        accountService.saveAccount(accountFrom);
        accountService.saveAccount(accountTo);
        saveDeposit(deposit);

        return appUser.getEmail() + " " +accountTo.getNr().toString();
    }
}
