
package com.example.BankApplication.transfer;

import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransferService {
    private final TransferRepository transferRepository;

    private final AccountService accountService;
    public void saveTransfer(Transfer transfer){
        transferRepository.save(transfer);
    }

    public Optional<Transfer> getTransfer(Transfer transfer){
        return transferRepository.findById(transfer.getId());
    }

    public String transfer(TransferRequest request) {

//        boolean isValidEmail = emailValidator.
//                test(request.getEmail());

        if (!accountService.getByNr(request.getAccountNR()).isEmpty())
            throw new IllegalStateException("No such account nr!");

//        Transfer transfer = new Transfer(
//                LocalDateTime.now(),
//                appUser,
//                request.getAccountNR(),
//                request.getAmountOfMoney()
//        );




        return "Transfer sent successfully!";
    }

}

