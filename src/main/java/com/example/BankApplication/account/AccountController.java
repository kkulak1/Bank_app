package com.example.BankApplication.account;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpClient;

@RestController
@AllArgsConstructor
//@RequestMapping(path = "dashboard")
public class AccountController {
    private final AccountService accountService;

    @RequestMapping(value = "/dashboard/add-account", method = RequestMethod.POST)
    public String addAccount(AccountRequest accountRequest, HttpSession httpSession){
        return accountService.createAccount(accountRequest, httpSession);

//        return "redirect:/dashboard";
    }
}
