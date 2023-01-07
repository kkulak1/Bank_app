package com.example.BankApplication.deposit;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/deposit")
@AllArgsConstructor
public class DepositController {
    private final DepositService depositService;

    @PostMapping
    public String sendDeposit(@RequestBody DepositRequest request) throws AccountNotFoundException {
        return depositService.deposit(request);
    }
}
