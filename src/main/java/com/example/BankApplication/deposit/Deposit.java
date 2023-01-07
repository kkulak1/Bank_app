package com.example.BankApplication.deposit;

import com.example.BankApplication.appuser.AppUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Deposit {
    @SequenceGenerator(
            name = "deposit_sequence",
            sequenceName = "deposit_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "deposit_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private LocalDateTime currentDepositDate;

    //    tutaj nie jestem pewien czy many:1
    @Column(nullable = false)
    private Long accountNR;
    @Column(nullable = false)
    private Float amountOfMoney;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUserFrom;

    public Deposit(LocalDateTime date, AppUser appUserFrom, Long accountNrTo, float amountOfMoney) {
        this.currentDepositDate = date;
        this.appUserFrom = appUserFrom;
        this.accountNR = accountNrTo;
        this.amountOfMoney = amountOfMoney;
    }
}
