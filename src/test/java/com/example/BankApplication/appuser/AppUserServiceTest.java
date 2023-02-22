package com.example.BankApplication.appuser;

import com.example.BankApplication.registration.token.ConfirmationTokenService;
import com.example.BankApplication.security.util.JwtUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class) // insted of autoclosable and @AfterEach tearDown
class AppUserServiceTest {
    @Mock
    private AppUserRepository appUserRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private ConfirmationTokenService confirmationTokenService;
    @Mock
    private JwtUtil jwtUtil;
    private AppUserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new AppUserService(appUserRepository, bCryptPasswordEncoder, confirmationTokenService, jwtUtil);
    }

    @Test
    void CanFindAllAppUsers() {
        //when
        underTest.findAllAppUsers();
        //then
        verify(appUserRepository).findAll();
    }

    @Test
    void canSignUpUser() {
        //given
        AppUser appUser = new AppUser(
                "Kamil",
                "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );
        //when
        underTest.signUpUser(appUser);
        //then
        //check if appUserRepo was invoked with the same appUser
        ArgumentCaptor<AppUser> appUserArgumentCaptor =
                ArgumentCaptor.forClass(AppUser.class);
        verify(appUserRepository).save(appUserArgumentCaptor.capture());

        AppUser capturedAppUser = appUserArgumentCaptor.getValue();
        assertThat(capturedAppUser).isEqualTo(appUser);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        AppUser appUser = new AppUser(
                "Kamil",
                "Kulak",
                "kamilkulak32@gmail.com",
                "password",
                AppUserRole.USER
        );

        given(appUserRepository
                .findByEmail(appUser.getEmail()))  // anyString() instead of email
                .willReturn(Optional.of(appUser));

        //when
        //then
        assertThatThrownBy(() ->underTest.signUpUser(appUser))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email already taken");

        // check if appUserRepo invokes save - it shouldnt
        verify(appUserRepository, never()).save(any());
    }
}