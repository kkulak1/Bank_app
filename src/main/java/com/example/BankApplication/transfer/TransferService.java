
package com.example.BankApplication.transfer;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.AbstractCollection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransferService {
    private final TransferRepository transferRepository;
    private final AppUserService appUserService;
    private final AccountService accountService;
    private final AppUserResource appUserResource;
    public void saveTransfer(Transfer transfer){
        transferRepository.save(transfer);
    }

    public Optional<Transfer> getTransfer(Transfer transfer){
        return transferRepository.findById(transfer.getId());
    }
    public RedirectView transfer(TransferRequest request) throws AccountNotFoundException {
//        AppUser appUser = appUserService.getCUrrentUser();

        String email = appUserResource.getUsername();
        AppUser appUser = appUserService.findAppUserByUsername(email);

        if (accountService.getByNr(request.getAccountNrTo()).isEmpty())
            throw new IllegalStateException("No such account nr!");

        if (accountService.getByNr(request.getAccountNrFrom()).isEmpty())
            throw new IllegalStateException("No such account nr!");

        Account accountFrom = accountService.findAccountByNr(request.getAccountNrFrom());
        Account accountTo = accountService.findAccountByNr(request.getAccountNrTo());

        if (accountFrom.getBalance() < request.getAmountOfMoney()){
            throw new IllegalStateException("Not enough money!");
        }

        Transfer transfer = new Transfer(
                LocalDateTime.now(),
                appUser,
                request.getAccountNrTo(),
                request.getAmountOfMoney()
        );

        accountFrom.setBalance(accountFrom.getBalance() - request.getAmountOfMoney());
        accountTo.setBalance(accountTo.getBalance() + request.getAmountOfMoney());

        accountService.saveAccount(accountFrom);
        accountService.saveAccount(accountTo);
        saveTransfer(transfer);

        return new RedirectView("/dashboard");
    }
}

