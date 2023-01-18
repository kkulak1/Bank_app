
package com.example.BankApplication.transfer;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserService;
import com.example.BankApplication.transactionHistory.TransactionHistory;
import com.example.BankApplication.transactionHistory.TransactionHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.AbstractCollection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransferService {
    private final TransferRepository transferRepository;
    private final AppUserService appUserService;
    private final AccountService accountService;
    private final AppUserResource appUserResource;
    private final TransactionHistoryRepository transactionHistoryRepository;
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

        double money = Double.parseDouble((request.getAmountOfMoney()));


        if (Objects.equals(accountFrom.getId(), accountTo.getId())) {
            TransactionHistory transactionHistory = new TransactionHistory(
                    appUser,
                    request.getAccountNrFrom(),
                    "Transfer",
                    Double.parseDouble(request.getAmountOfMoney()),
                    "failed",
                    "transfer between one account",
                    LocalDateTime.now()
            );
            transactionHistoryRepository.save(transactionHistory);

            throw new IllegalStateException("Cannot transfer to the same account!");
        }

        if (accountFrom.getBalance().compareTo(BigDecimal.valueOf(money)) < 0){
            TransactionHistory transactionHistory = new TransactionHistory(
                    appUser,
                    request.getAccountNrFrom(),
                    "Transfer",
                    Double.parseDouble(request.getAmountOfMoney()),
                    "failed",
                    "not enough funds",
                    LocalDateTime.now()
            );
            transactionHistoryRepository.save(transactionHistory);

            throw new IllegalStateException("Not enough money!");
        }


        Transfer transfer = new Transfer(
                LocalDateTime.now(),
                appUser,
                request.getAccountNrTo(),
                money
        );

        accountFrom.setBalance(accountFrom.getBalance().subtract(BigDecimal.valueOf(money)));
        accountTo.setBalance(accountTo.getBalance().add(BigDecimal.valueOf(money)));

        accountService.saveAccount(accountFrom);
        accountService.saveAccount(accountTo);
        saveTransfer(transfer);

        TransactionHistory transactionHistory = new TransactionHistory(
                appUser,
                request.getAccountNrFrom(),
                "Transfer",
                Double.parseDouble(request.getAmountOfMoney()),
                "success",
                "Transfer Successful",
                LocalDateTime.now()
        );
        transactionHistoryRepository.save(transactionHistory);

        return new RedirectView("/dashboard");
    }

    public List<Transfer> findAllTransfers(AppUser appUser) {
        return transferRepository.findAllTransfers(appUser);
    }
}

