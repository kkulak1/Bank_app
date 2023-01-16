package com.example.BankApplication.transfer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping(path="/transfer")
@AllArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    public RedirectView sendTransfer(@RequestBody TransferRequest request) throws AccountNotFoundException {
        return transferService.transfer(request);
    }
}
