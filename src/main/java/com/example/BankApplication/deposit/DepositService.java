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
    //    @Transactional
    public String deposit(DepositRequest request) throws AccountNotFoundException {

//        boolean isValidEmail = emailValidator.
//                test(request.getEmail());

        AppUser appUser = appUserService.getCUrrentUser();

        if (accountService.getByNr(request.getAccountNR()).isEmpty())
            throw new IllegalStateException("No such account nr!");


        Deposit deposit = new Deposit(
                LocalDateTime.now(),
                appUser,
                request.getAccountNR(),
                request.getAmountOfMoney()
        );

//        return transfer.getAccountNR().toString() +" , "+ transfer.getCurrentDate().toString() +" , "+ transfer.getAppUserFrom().getEmail() +" , "+ transfer.getAmountOfMoney().toString();


        Account accountTo = accountService.findAccountByNr(request.getAccountNR());
        accountTo.setBalance(accountTo.getBalance() + request.getAmountOfMoney());


        saveDeposit(deposit);

        return appUser.getEmail() + " " +accountTo.getNr().toString();
    }
}
