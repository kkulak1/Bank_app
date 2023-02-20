package com.example.BankApplication.transactionHistory;

import com.example.BankApplication.appuser.AppUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class TransactionHistory {
    @SequenceGenerator(
            name = "transaction_history_sequence",
            sequenceName = "transaction_history_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_history_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUserFrom;
    @Column(nullable = false)
    private Long accountNrFrom;
    @Column(nullable = false)
    private String transactionType;
    @Column(nullable = false)
    private Double paymentAmount;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String reasonCode;
    @Column(nullable = false)
    private LocalDateTime currentTransactionDate;

    public TransactionHistory(AppUser appUser, Long accountNrFrom, String transactionType, Double paymentAmount, LocalDateTime date) {
        this.appUserFrom = appUser;
        this.accountNrFrom = accountNrFrom;
        this.transactionType = transactionType;
        this.paymentAmount = paymentAmount;
        this.status = "status";
        this.reasonCode = "reasonCode";
        this.currentTransactionDate = date;
    }
}
