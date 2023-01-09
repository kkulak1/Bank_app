package com.example.BankApplication.payment;

import com.example.BankApplication.account.Account;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Payment {
    @SequenceGenerator(
            name = "payment_sequence",
            sequenceName = "payment_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String beneficiary;
    @Column(nullable = false)
    private Long beneficiaryAccountNr;
//    @ManyToOne
//    @JoinColumn(
//            nullable = false,
//            name = "account_id"
//    )
    @Column(nullable = false)
    private Long accountNrTo;
    @Column(nullable = false)
    private String reference;
    @Column(nullable = false)
    private Long paymentAmount;

    public Payment(String beneficiary, Long beneficiaryAccountNr, Long accountNrTo, String reference, Long paymentAmount) {
        this.beneficiary = beneficiary;
        this.beneficiaryAccountNr = beneficiaryAccountNr;
        this.accountNrTo = accountNrTo;
        this.reference = reference;
        this.paymentAmount = paymentAmount;
    }
}
