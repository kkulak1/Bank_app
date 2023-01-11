package com.example.BankApplication.account;

import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@AllArgsConstructor
//@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AppUserService appUserService;

    public Optional<Account> getByNr(Long nr){
        return accountRepository.findByNr(nr);
    }

    public void addAccount(Account account){
//        boolean userExists = appUserRepository
//                .findByEmail(appUser.getEmail())
//                .isPresent();

        account.setNr(generateAccountNr());

        accountRepository.save(account);
    }

    public Long generateAccountNr(){
        StringBuilder generatedNr = new StringBuilder();

//        TODO: check if number already in database
        boolean isInDatabase = true;

        while (isInDatabase) {
            for (int i = 0; i < 8; i++) {
                int randomNr = (int) (Math.random() * (9) + 1);
                generatedNr.append(randomNr);
            }
            Long Number = Long.parseLong(generatedNr.toString());
            if (!getByNr(Number).isPresent()){
                isInDatabase = false;
            }
        }

        return Long.parseLong(generatedNr.toString());
    }
    public Account findAccountByAppUser(AppUser appUser) throws AccountNotFoundException {
        return accountRepository.findByAppUser(appUser)
                .orElseThrow(()->
                        new AccountNotFoundException(String.format("Account not faound!")));
    }

    public Account findAccountByNr(Long accountNR) throws AccountNotFoundException {
        return accountRepository.findByNr(accountNR)
                .orElseThrow(()->
                        new AccountNotFoundException(String.format("Account not found!")));
    }

    public String createAccount(AccountRequest accountRequest, HttpSession httpSession) {
        String email = (String) httpSession.getAttribute("username");

        AppUser appUser = appUserService.findAppUserByUsername(email);

        float balance = 0.0F;

        Account newAccount = new Account(
                appUser,
                balance
        );

        addAccount(newAccount);

        return "Account created successfully.";
    }

    public void saveAccount(Account account){
        accountRepository.save(account);
    }
}
