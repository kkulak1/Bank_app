package com.example.BankApplication.registration;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
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
