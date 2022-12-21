
package com.example.BankApplication.transfer;

import com.example.BankApplication.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransferService {
    private final TransferRepository transferRepository;
    private final AppUserService appUserService;
    private final TransferValidator transferValidator;

    public void saveTransfer(Transfer transfer){
        transferRepository.save(transfer);
    }

    public Optional<Transfer> getTransfer(String transfer){
        return transferRepository.findByTransfer(transfer);
    }

    public String transfer(TransferRequest request) {
//        boolean isValidEmail = emailValidator.
//                test(request.getEmail());
        boolean isValidNr = transferValidator
                .testTransfer(request.getAccountNR(), request.getAmountOfMoney());
        return "String ";
    }
//        Transfer transfer = new Transfer(
//                LocalDateTime.now(),
//                request.getAccountNR(),
//                request.getAmountOfMoney()
//        )
}

