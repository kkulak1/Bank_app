package com.example.BankApplication.withdraw;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserService;

import com.example.BankApplication.transactionHistory.TransactionHistory;
import com.example.BankApplication.transactionHistory.TransactionHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WithdrawService {
    private final WithdrawRepository withdrawRepository;
    private final AppUserService appUserService;
    private final AccountService accountService;
    private final AppUserResource appUserResource;
    private final TransactionHistoryRepository transactionHistoryRepository;
    public void saveWithdraw(Withdraw withdraw){
        withdrawRepository.save(withdraw);
    }

    public Optional<Withdraw> getWithdraw(Withdraw withdraw){
        return withdrawRepository.findById(withdraw.getId());
    }
    public RedirectView withdraw(WithdrawRequest request) throws AccountNotFoundException {

//        AppUser appUser = appUserService.getCUrrentUser();

        String email = appUserResource.getUsername();
        AppUser appUser = appUserService.findAppUserByUsername(email);

        if (accountService.getByNr(request.getAccountNR()).isEmpty())
            throw new IllegalStateException("No such account nr!");

        Account accountFrom = accountService.findAccountByNr(request.getAccountNR());

        double money = Double.parseDouble(request.getAmountOfMoney());

        if (accountFrom.getBalance().compareTo(BigDecimal.valueOf(money)) < 0) {
            TransactionHistory transactionHistory = new TransactionHistory(
                    appUser,
                    request.getAccountNR(),
                    "Withdraw",
                    Double.parseDouble(request.getAmountOfMoney()),
                    "failed",
                    "not enough funds",
                    LocalDateTime.now()
            );
            transactionHistoryRepository.save(transactionHistory);

            throw new IllegalStateException("Not enough money in account!");
        }


        Withdraw withdraw = new Withdraw(
                LocalDateTime.now(),
                appUser,
                request.getAccountNR(),
                money
        );

        accountFrom.setBalance(accountFrom.getBalance().subtract(BigDecimal.valueOf(money)));

        accountService.saveAccount(accountFrom);

        saveWithdraw(withdraw);

        TransactionHistory transactionHistory = new TransactionHistory(
                appUser,
                request.getAccountNR(),
                "Withdraw",
                Double.parseDouble(request.getAmountOfMoney()),
                "success",
                "withdraw successful",
                LocalDateTime.now()
        );
        transactionHistoryRepository.save(transactionHistory);

        return new RedirectView("/dashboard");
    }

    public List<Withdraw> findAllWithdraws(AppUser appUser) {
        return withdrawRepository.findAllWithdraws(appUser);
    }
}
