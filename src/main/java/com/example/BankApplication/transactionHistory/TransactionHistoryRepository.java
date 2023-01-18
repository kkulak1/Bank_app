package com.example.BankApplication.transactionHistory;

import com.example.BankApplication.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    Optional<TransactionHistory> findById(Long id);

    @Query(value = "SELECT a from TransactionHistory a where a.appUserFrom = :appUser")
    List<TransactionHistory> findAllTransactions(AppUser appUser);

}
