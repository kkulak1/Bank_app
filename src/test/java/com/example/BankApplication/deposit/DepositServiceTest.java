package com.example.BankApplication.deposit;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserRole;
import com.example.BankApplication.appuser.AppUserService;
import com.example.BankApplication.transactionHistory.TransactionHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepositServiceTest {
    @Mock
    private DepositRepository depositRepository;
    @Mock
    private AppUserService appUserService;
    @Mock
    private AccountService accountServiceUnderTest;
    @Mock
    private AppUserResource appUserResource;
    @Mock
    private TransactionHistoryService transactionHistoryService;
    private DepositService underTest;

    @BeforeEach
    void setUp(){
        underTest = new DepositService(
                depositRepository,
                appUserService,
                accountServiceUnderTest,
                appUserResource,
                transactionHistoryService
        );
    }

    @Test
    void shouldSaveDeposit() {
        //given
        AppUser appUser = new AppUser(
                "Kamil",
                "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );

        Deposit deposit = new Deposit(
                LocalDateTime.now(),
                appUser,
                12345678L,
                100.0
        );

        //when
        underTest.saveDeposit(deposit);

        //then
        verify(depositRepository).save(deposit);
    }

    @Test
    void ShouldGetDeposit() {
        //given
        AppUser appUser = new AppUser(
                "Kamil",
                "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );

        Deposit deposit = new Deposit(
                LocalDateTime.now(),
                appUser,
                12345678L,
                100.0
        );

        //when
        underTest.getDeposit(deposit);

        //then
        verify(depositRepository).findById(deposit.getId());
    }

    @Test
    void ShouldFindAllDeposits() {
        AppUser appUser = new AppUser(
                "Kamil",
                "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );

        //when
        underTest.findAllDeposits(appUser);

        //then
        verify(depositRepository).findAllDeposits(appUser);
    }

    @Test
    @Disabled
    void canMakeDeposit() {
        //given
        AppUser appUser = new AppUser(
                "Kamil",
                "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );
        appUserService.signUpUser(appUser);

        Account account = new Account(
                appUser, new BigDecimal(100), "korzystne","Oszczednosciowe"
        );
        account.setNr(12345678L);
        accountServiceUnderTest.saveAccount(account);

        DepositRequest depositRequest = new DepositRequest(
                12345678L,
                "100"
        );

        //when
//        underTest.deposit(depositRequest);

        //then
        ArgumentCaptor<Deposit> depositArgumentCaptor =
                ArgumentCaptor.forClass(Deposit.class);

        verify(depositRepository).save(depositArgumentCaptor.capture());
    }
}


