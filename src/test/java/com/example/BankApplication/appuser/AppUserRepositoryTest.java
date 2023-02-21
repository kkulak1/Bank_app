package com.example.BankApplication.appuser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AppUserRepositoryTest {
    @Autowired
    private AppUserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();      // delete everyone after
    }

    @Test
    void ItShouldCheckIfStudentEmailExists() {
        //given
        AppUser appUser = new AppUser(
                "Kamil",
                 "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );
        underTest.save(appUser);
        //when
        int exists = underTest.selectExistsEmail("kamilkulak32@gmail.com");

        int shouldntexist = underTest.selectExistsEmail("mamama@gmail.com");

        //then

        assertThat(exists).isEqualTo(1);

        assertThat(shouldntexist).isEqualTo(0);
    }

    @Test
    void ItShouldCheckIfStudentEmailDoesntExists() {
        //given
        String email = "kamilkulak32@gmail.com";
        //when
        int shouldntexist = underTest.selectExistsEmail(email);

        //then
        assertThat(shouldntexist).isEqualTo(0);
    }
}