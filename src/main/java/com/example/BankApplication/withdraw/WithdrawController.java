package com.example.BankApplication.withdraw;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/withdraw")
@AllArgsConstructor
public class WithdrawController {
    private final WithdrawService withdrawService;

    @PostMapping
    public RedirectView sendWithdraw(@RequestBody WithdrawRequest request) throws AccountNotFoundException {
        return withdrawService.withdraw(request);
    }
}
