package com.example.BankApplication.account;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AccountRequest {
    private String name;
    private String accountType;
}
