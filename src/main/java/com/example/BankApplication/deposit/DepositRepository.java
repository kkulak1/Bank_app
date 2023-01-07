package com.example.BankApplication.deposit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface DepositRepository extends JpaRepository<Deposit, Long> {
    Optional<Deposit> findById(Long id);
}
