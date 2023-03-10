package com.example.BankApplication.deposit;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserService;
import com.example.BankApplication.transactionHistory.TransactionHistory;
import com.example.BankApplication.transactionHistory.TransactionHistoryService;
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
public class DepositService {
    private final DepositRepository depositRepository;
    private final AppUserService appUserService;
    private final AccountService accountService;
    private final AppUserResource appUserResource;
    private final TransactionHistoryService transactionHistoryService;

    public void saveDeposit(Deposit deposit) {
        depositRepository.save(deposit);
    }

    public Optional<Deposit> getDeposit(Deposit deposit) {
        return depositRepository.findById(deposit.getId());
    }

    public RedirectView deposit(DepositRequest request) throws AccountNotFoundException {
        String email = appUserResource.getUsername();
        AppUser appUser = appUserService.findAppUserByUsername(email);

        boolean accountNrExists = accountService.getByNr(request.getAccountNR())
                .isPresent();

        if (!accountNrExists)
            throw new IllegalStateException("No such account nr!");

        Account accountFrom = accountService.findAccountByNr(request.getAccountNR());

        Deposit deposit = new Deposit(
                LocalDateTime.now(),
                appUser,
                request.getAccountNR(),
                Double.parseDouble(request.getAmountOfMoney())
        );

        BigDecimal currentBalance = accountFrom.getBalance();
        BigDecimal money = BigDecimal.valueOf(deposit.getAmountOfMoney());

        accountFrom.setBalance(currentBalance.add(money));

        accountService.saveAccount(accountFrom);

        saveDeposit(deposit);

        TransactionHistory transactionHistory = new TransactionHistory(
                appUser,
                request.getAccountNR(),
                "Deposit",
                Double.parseDouble(request.getAmountOfMoney()),
                LocalDateTime.now()
        );
        transactionHistoryService.setTransactionStatusAndType(transactionHistory, true, "deposit successful");

        return new RedirectView("/dashboard");
    }

    public List<Deposit> findAllDeposits(AppUser appUser) {
        return depositRepository.findAllDeposits(appUser);
    }
}
