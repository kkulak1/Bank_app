package com.example.BankApplication.transfer;

import com.example.BankApplication.appuser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
//@AllArgsConstructor
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
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUserFrom;

//    tutaj nie jestem pewien czy many:1
//    @OneToOne
    private Long accountNR;
    private Float amountOfMoney;


    public Transfer(LocalDateTime date, AppUser appUserFrom, Long accountNrTo, float amountOfMoney) {
        this.date = date;
        this.appUserFrom = appUserFrom;
        this.accountNR = accountNrTo;
        this.amountOfMoney = amountOfMoney;
    }

//    public String transferMoney(){
//        return "Money transfered succesfully!";
//    }
}