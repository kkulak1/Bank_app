package com.example.BankApplication.account;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "dashboard/add-account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public String addAccount(AccountRequest accountRequest){
        return accountService.createAccount(accountRequest);
    }
}
