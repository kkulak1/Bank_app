package com.example.BankApplication.account;

import lombok.*;

import javax.servlet.http.HttpSession;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AccountRequest {
    private String account_name;
    private String account_type;
    HttpSession httpSession;
}
