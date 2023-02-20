package com.example.BankApplication.transactionHistory;

import com.example.BankApplication.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionHistoryService {
    private final TransactionHistoryRepository transactionHistoryRepository;

    public Optional<TransactionHistory> getTransactionHistory(TransactionHistory transactionHistory){
        return transactionHistoryRepository.findById(transactionHistory.getId());
    }

    public void addTransactionHistory(AppUser appUser,
                                      Long accountNr,
                                      String type,
                                      Double amountOfMoney){
        TransactionHistory transactionHistoryObj = new TransactionHistory(
                appUser, accountNr,
                type, amountOfMoney, LocalDateTime.now());
        transactionHistoryRepository.save(transactionHistoryObj);
    }

    public void setTransactionStatusAndType(TransactionHistory transactionHistory, boolean status, String type){
        if (status){
            transactionHistory.setStatus("success");}
        else {
            transactionHistory.setStatus("failed");
        }
        transactionHistory.setReasonCode(type);
        transactionHistoryRepository.save(transactionHistory);
    }

    public void saveTransactionHistory(TransactionHistory transactionHistory){
        transactionHistoryRepository.save(transactionHistory);
    }
}
