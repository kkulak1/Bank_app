package com.example.BankApplication.deposit;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountRepository;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserRepository;
import com.example.BankApplication.appuser.AppUserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepositRepositoryTest {
    @Autowired
    private  DepositRepository underTest;
    @Autowired
    private AppUserRepository underTestAppUserRepo;
    @Autowired
    private AccountRepository underTestAccountRepo;

    @Test
    void findAllDeposits() {
        //given
        AppUser appUser = new AppUser(
                "Kamil",
                "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );
        underTestAppUserRepo.save(appUser);

        Account account = new Account(
                appUser, new BigDecimal(100), "korzystne","Oszczednosciowe"
        );
        account.setNr(12345678L);
        underTestAccountRepo.save(account);

        Deposit deposit = new Deposit(
                LocalDateTime.now(), appUser, 12345678L, 100.0
        );
        underTest.save(deposit);
        //when
        List<Deposit> depositList = underTest.findAllDeposits(appUser);
        //then
        assertThat(depositList).isNotEmpty();
    }
}