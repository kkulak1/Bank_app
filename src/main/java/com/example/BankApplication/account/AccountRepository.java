package com.example.BankApplication.account;

import com.example.BankApplication.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNr(Long nr);
    Optional<Account> findByAppUser(AppUser appUser);

    Optional<Account> findByName(String name);

    @Query(value = "SELECT a from Account a where a.appUser = :appUser")
    List<Account> findAllAccounts(AppUser appUser);

    @Query(value = "SELECT sum(a.balance) from Account a where a.appUser = :appUser")
    BigDecimal getTotalBalance(AppUser appUser);
}
