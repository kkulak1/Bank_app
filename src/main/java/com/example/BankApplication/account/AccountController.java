package com.example.BankApplication.account;


import com.example.BankApplication.appuser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpClient;
import java.security.Principal;

@RestController
@AllArgsConstructor
//@RequestMapping(path = "dashboard")
public class AccountController {
    private final AccountService accountService;

    @RequestMapping(value = "/dashboard/add-account", method = RequestMethod.POST)
    public String addAccount(AccountRequest accountRequest){
        return accountService.createAccount(accountRequest);

//        return "redirect:/dashboard";
    }
}
