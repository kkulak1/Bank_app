package com.example.BankApplication.transfer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TransferRequest {
    private Long accountNrFrom;
    private Long accountNrTo;
    private Float amountOfMoney;
}
