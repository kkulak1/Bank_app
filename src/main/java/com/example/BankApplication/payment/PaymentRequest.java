package com.example.BankApplication.payment;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PaymentRequest {
    private Long beneficiaryAccountNr;
    private String beneficiary;
    private Long accountNrFrom;
    private String reference;
    private String paymentAmount;
}
