package com.example.BankApplication.registration;

import lombok.*;

//co chcemy przechwycic gdy wyslemy zapytanie-request
@Getter
@Setter
@AllArgsConstructor
//@RequiredArgsConstructor
//@NoArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirm_password;
}
