package com.example.BankApplication.deposit;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DepositRequest {
    private Long accountNR;
    private String amountOfMoney;
}
