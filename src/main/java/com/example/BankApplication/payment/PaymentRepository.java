package com.example.BankApplication.payment;

import com.example.BankApplication.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findById(Long id);

    @Query(value = "SELECT a from Payment a where a.appUserFrom = :appUser")
    List<Payment> findAllPayments(AppUser appUser);
}
