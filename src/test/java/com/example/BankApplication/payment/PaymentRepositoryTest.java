package com.example.BankApplication.payment;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountRepository;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserRepository;
import com.example.BankApplication.appuser.AppUserRole;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PaymentRepositoryTest {
    @Autowired
    private PaymentRepository underTest;
    @Autowired
    private AppUserRepository underTestAppUser;
    @Autowired
    private AccountRepository underTestAccountRepository;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findById() {
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
        underTestAccountRepository.save(account);

        Payment payment = new Payment(
                "beneficiary",
                12334567L,
                12345678L,
                "reference",
                100.0,
                appUser,
                LocalDateTime.now()
        );

        underTest.save(payment);

        //when
        Optional<Payment> paymentReturned = underTest.findById(payment.getId());

        //then
        assertThat(paymentReturned).isPresent();
    }

    @Test
    void findAllPayments() {
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
        underTestAccountRepository.save(account);

        Payment payment = new Payment(
                "beneficiary",
                12334567L,
                12345678L,
                "reference",
                100.0,
                appUser,
                LocalDateTime.now()
        );

        underTest.save(payment);

        //when
        List<Payment> paymentList = underTest.findAllPayments(appUser);

        //then
        assertThat(paymentList).isNotEmpty();
    }
}