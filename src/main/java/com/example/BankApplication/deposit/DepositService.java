package com.example.BankApplication.deposit;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepositService {
    private final DepositRepository depositRepository;
    private final AppUserService appUserService;
    private final AccountService accountService;
    private final AppUserResource appUserResource;
    public void saveDeposit(Deposit deposit){
        depositRepository.save(deposit);
    }

    public Optional<Deposit> getDeposit(Deposit deposit){
        return depositRepository.findById(deposit.getId());
    }
    public RedirectView deposit(DepositRequest request) throws AccountNotFoundException {
//        AppUser appUser = appUserService.getCUrrentUser();

        String email = appUserResource.getUsername();
        AppUser appUser = appUserService.findAppUserByUsername(email);

        if (accountService.getByNr(request.getAccountNR()).isEmpty())
            throw new IllegalStateException("No such account nr!");

        Account accountFrom = accountService.findAccountByNr(request.getAccountNR());

        Deposit deposit = new Deposit(
                LocalDateTime.now(),
                appUser,
                request.getAccountNR(),
                request.getAmountOfMoney()
        );

        accountFrom.setBalance(accountFrom.getBalance() + request.getAmountOfMoney());

        accountService.saveAccount(accountFrom);

        saveDeposit(deposit);

        return new RedirectView("/dashboard");
    }

    public List<Deposit> findAllDeposits(AppUser appUser) {
        return depositRepository.findAllDeposits(appUser);
    }
}
