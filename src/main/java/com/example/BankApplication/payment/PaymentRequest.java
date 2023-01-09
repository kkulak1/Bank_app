package com.example.BankApplication.payment;

import com.example.BankApplication.account.Account;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PaymentRequest {
    private Long beneficiaryAccountNr;
    private String beneficiary;
    private Long accountNrTo;
    private String reference;
    private Long paymentAmount;
}
