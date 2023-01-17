package com.example.BankApplication.withdraw;

import com.example.BankApplication.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
    Optional<Withdraw> findById(Long id);

    @Query(value = "SELECT a from Withdraw a where a.appUserFrom = :appUser")
    List<Withdraw> findAllWithdraws(AppUser appUser);
}
