package com.example.BankApplication.account;

import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserRepository;
import com.example.BankApplication.appuser.AppUserRole;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository underTest;
    @Autowired
    private AppUserRepository underTestAppUser;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findAllAccountsExists() {
        //given
        AppUser appUser = new AppUser(
                "Kamil",
                "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );
        underTestAppUser.save(appUser);

        Account account = new Account(
                appUser, new BigDecimal(100), "korzystne","Oszczednosciowe"
        );
        account.setNr(12345678L);
        underTest.save(account);

        //when
        List<Account> accounts = underTest.findAllAccounts(appUser);

        //then
        assertThat(accounts).isNotEmpty();
    }

    @Test
    void findAllAccountsDoesntExists() {
        //given
        AppUser appUser = new AppUser(
                "Kamil",
                "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );
        underTestAppUser.save(appUser);

        //when
        List<Account> accounts = underTest.findAllAccounts(appUser);

        //then
        assertThat(accounts).isEmpty();
    }

    @Test
    void getTotalBalance() {
        //given
        AppUser appUser = new AppUser(
                "Kamil",
                "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );
        underTestAppUser.save(appUser);

        Account account = new Account(
                appUser, new BigDecimal(100), "korzystne","Oszczednosciowe"
        );
        account.setNr(12345678L);
        underTest.save(account);

        //when
        BigDecimal balance = underTest.getTotalBalance(appUser);

        //then
        assertThat(balance).isCloseTo(new BigDecimal(100), Percentage.withPercentage(1));
    }

    @Test
    void getTotalBalanceFromMultipleAccounts() {
        //given
        AppUser appUser = new AppUser(
                "Kamil",
                "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );
        underTestAppUser.save(appUser);

        Account account = new Account(
                appUser, new BigDecimal(100), "korzystne","Oszczednosciowe"
        );
        account.setNr(12345678L);
        underTest.save(account);

        Account account2 = new Account(
                appUser, new BigDecimal("100.20"), "korzystne","Oszczednosciowe"
        );
        account2.setNr(12345478L);
        underTest.save(account2);

        //when
        BigDecimal balance = underTest.getTotalBalance(appUser);

        //then
        assertThat(balance).isCloseTo(new BigDecimal("200.20"), Percentage.withPercentage(1));
    }
}