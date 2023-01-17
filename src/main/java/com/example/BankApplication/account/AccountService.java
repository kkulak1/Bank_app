package com.example.BankApplication.account;

import com.example.BankApplication.Storage.UserStorage;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
//@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AppUserService appUserService;
    private final AppUserResource appUserResource;

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

    public Account findAccountByName(String name) throws AccountNotFoundException {
        return accountRepository.findByName(name)
                .orElseThrow(()->
                        new AccountNotFoundException(String.format("Account not found!")));
    }


    public RedirectView createAccount(AccountRequest accountRequest) {
        String email = appUserResource.getUsername();
        AppUser appUser = appUserService.findAppUserByUsername(email);

        float balance = 0.0F;

        Account newAccount = new Account(
                appUser,
                balance,
                accountRequest.getAccount_name(),
                accountRequest.getAccount_type()
        );

        addAccount(newAccount);

        return new RedirectView("/dashboard");
    }
    public List<Account> findAllAcc(AppUser appUser){
        return accountRepository.findAllAccounts(appUser);
    }

    public void saveAccount(Account account){
        accountRepository.save(account);
    }
}
