package com.example.BankApplication.account;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@AllArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @RequestMapping(value = "/dashboard/add-account", method = RequestMethod.POST)
    public RedirectView addAccount(AccountRequest accountRequest) {
        return accountService.createAccount(accountRequest);
    }
}
