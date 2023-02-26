package com.example.BankApplication.account;

import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AppUserService appUserService;
    private final AppUserResource appUserResource;

    public Optional<Account> getByNr(Long nr) {
        return accountRepository.findByNr(nr);
    }

    public void addAccount(Account account) {
        account.setNr(generateAccountNr());

        accountRepository.save(account);
    }

    public Long generateAccountNr() {
        StringBuilder generatedNr = new StringBuilder();

        boolean isInDatabase = true;

        while (isInDatabase) {
            for (int i = 0; i < 8; i++) {
                int randomNr = (int) (Math.random() * (9) + 1);
                generatedNr.append(randomNr);
            }
            Long Number = Long.parseLong(generatedNr.toString());
            if (!getByNr(Number).isPresent()) {
                isInDatabase = false;
            }
        }

        return Long.parseLong(generatedNr.toString());
    }

    public Account findAccountByAppUser(AppUser appUser) throws AccountNotFoundException {
        return accountRepository.findByAppUser(appUser)
                .orElseThrow(() ->
                        new AccountNotFoundException(String.format("Account not faound!")));
    }

    public Account findAccountByNr(Long accountNR) throws AccountNotFoundException {
        return accountRepository.findByNr(accountNR)
                .orElseThrow(() ->
                        new AccountNotFoundException(String.format("Account not found!")));
    }

    public Account findAccountByName(String name) throws AccountNotFoundException {
        return accountRepository.findByName(name)
                .orElseThrow(() ->
                        new AccountNotFoundException(String.format("Account not found!")));
    }

    public RedirectView createAccount(AccountRequest accountRequest) {
        String email = appUserResource.getUsername();
        AppUser appUser = appUserService.findAppUserByUsername(email);

        BigDecimal balance = BigDecimal.valueOf(0.0);

        Account newAccount = new Account(
                appUser,
                balance,
                accountRequest.getAccount_name(),
                accountRequest.getAccount_type()
        );

        addAccount(newAccount);

        return new RedirectView("/dashboard");
    }

    public List<Account> findAllAcc(AppUser appUser) {
        return accountRepository.findAllAccounts(appUser);
    }

    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}
