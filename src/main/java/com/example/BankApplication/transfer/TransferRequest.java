package com.example.BankApplication.transfer;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TransferRequest {
    private final Long accountNR;
    private final Float amountOfMoney;
}
