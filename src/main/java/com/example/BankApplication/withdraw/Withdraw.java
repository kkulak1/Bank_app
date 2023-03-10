package com.example.BankApplication.withdraw;

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
public class Withdraw {
    @SequenceGenerator(
            name = "withdraw_sequence",
            sequenceName = "withdraw_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "withdraw_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private LocalDateTime currentWithdrawDate;

    @Column(nullable = false)
    private Long accountNR;

    @Column(nullable = false)
    private Double amountOfMoney;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUserFrom;

    public Withdraw(LocalDateTime date, AppUser appUserFrom, Long accountNrFrom, double amountOfMoney) {
        this.currentWithdrawDate = date;
        this.appUserFrom = appUserFrom;
        this.accountNR = accountNrFrom;
        this.amountOfMoney = amountOfMoney;
    }
}
