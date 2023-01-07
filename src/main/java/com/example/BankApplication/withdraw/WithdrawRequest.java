package com.example.BankApplication.withdraw;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class WithdrawRequest {
    private Long accountNR;
    private Float amountOfMoney;
}
