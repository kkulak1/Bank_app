package com.example.BankApplication.transfer;

import com.example.BankApplication.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    Optional<Transfer> findById(Long id);

    @Query(value = "SELECT a from Transfer a where a.appUserFrom = :appUser")
    List<Transfer> findAllTransfers(AppUser appUser);
}
