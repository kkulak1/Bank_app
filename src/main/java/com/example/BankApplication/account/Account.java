package com.example.BankApplication.account;

import com.example.BankApplication.appuser.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Account {
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private Long nr;
    @Column(nullable = false)
    private float balance=0;
    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public Account(AppUser appUser, float balance) {
        this.appUser = appUser;
        this.balance = balance;
    }
}

