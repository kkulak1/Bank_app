package com.example.BankApplication.account;

import com.example.BankApplication.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByNr(Long nr);

//    @Override
    Optional<Account> findByAppUser(AppUser appUser);
}
