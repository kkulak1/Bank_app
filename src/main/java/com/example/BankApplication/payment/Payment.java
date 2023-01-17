package com.example.BankApplication.payment;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.appuser.AppUser;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Long accountNrFrom;
    @Column(nullable = false)
    private String reference;
    @Column(nullable = false)
    private Long paymentAmount;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUserFrom;

    @Column(nullable = false)
    private LocalDateTime currentPaymentDate;

    public Payment(String beneficiary, Long beneficiaryAccountNr, Long accountNrFrom, String reference, Long paymentAmount, AppUser appUser, LocalDateTime date) {
        this.beneficiary = beneficiary;
        this.beneficiaryAccountNr = beneficiaryAccountNr;
        this.accountNrFrom = accountNrFrom;
        this.reference = reference;
        this.paymentAmount = paymentAmount;
        this.appUserFrom = appUser;
        this.currentPaymentDate = date;
    }
}
