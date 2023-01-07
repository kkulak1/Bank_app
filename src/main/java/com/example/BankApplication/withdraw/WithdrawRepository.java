package com.example.BankApplication.withdraw;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
    Optional<Withdraw> findById(Long id);
}
