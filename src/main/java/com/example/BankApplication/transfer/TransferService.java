
package com.example.BankApplication.transfer;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void saveTransfer(Transfer transfer){
        transferRepository.save(transfer);
    }

    public Optional<Transfer> getTransfer(Transfer transfer){
        return transferRepository.findById(transfer.getId());
    }
//    @Transactional
    public String transfer(TransferRequest request) throws AccountNotFoundException {

//        boolean isValidEmail = emailValidator.
//                test(request.getEmail());

        AppUser appUser = appUserService.getCUrrentUser();

        if (accountService.getByNr(request.getAccountNR()).isEmpty())
            throw new IllegalStateException("No such account nr!");

        Account account = accountService.findAccountByAppUser(appUser);

        if (account.getBalance() < request.getAmountOfMoney()){
            throw new IllegalStateException("Not enough money!");
        }

        Transfer transfer = new Transfer(
                LocalDateTime.now(),
                appUser,
                request.getAccountNR(),
                request.getAmountOfMoney()
        );

//        return transfer.getAccountNR().toString() +" , "+ transfer.getCurrentDate().toString() +" , "+ transfer.getAppUserFrom().getEmail() +" , "+ transfer.getAmountOfMoney().toString();
//
        account.setBalance(account.getBalance() - request.getAmountOfMoney());

        Account account1 = accountService.findAccountByNr(request.getAccountNR());
        account1.setBalance(account1.getBalance() + request.getAmountOfMoney());

//        saveTransfer(transfer);
        saveTransfer(transfer);

        return appUser.getEmail() + " " + account.getNr() + " , " + account1.getNr().toString() + " ";
    }
}

