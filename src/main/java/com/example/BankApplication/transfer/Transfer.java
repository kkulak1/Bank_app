package com.example.BankApplication.transfer;

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
public class Transfer {
    @SequenceGenerator(
            name = "transfer_sequence",
            sequenceName = "transfer_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transfer_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private LocalDateTime currentTransferDate;

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

    public Transfer(LocalDateTime date, AppUser appUserFrom, Long accountNrTo, double amountOfMoney) {
        this.currentTransferDate = date;
        this.appUserFrom = appUserFrom;
        this.accountNR = accountNrTo;
        this.amountOfMoney = amountOfMoney;
    }
}
