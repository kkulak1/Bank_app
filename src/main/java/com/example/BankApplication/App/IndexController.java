package com.example.BankApplication.App;

import com.example.BankApplication.account.Account;
import com.example.BankApplication.account.AccountRepository;
import com.example.BankApplication.account.AccountService;
import com.example.BankApplication.appuser.AppUser;
import com.example.BankApplication.appuser.AppUserResource;
import com.example.BankApplication.appuser.AppUserService;
import com.example.BankApplication.deposit.Deposit;
import com.example.BankApplication.deposit.DepositService;
import com.example.BankApplication.payment.Payment;
import com.example.BankApplication.payment.PaymentService;
import com.example.BankApplication.transactionHistory.TransactionHistory;
import com.example.BankApplication.transactionHistory.TransactionHistoryRepository;
import com.example.BankApplication.transfer.Transfer;
import com.example.BankApplication.transfer.TransferService;
import com.example.BankApplication.withdraw.Withdraw;
import com.example.BankApplication.withdraw.WithdrawService;
import lombok.AllArgsConstructor;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@AllArgsConstructor
@Controller
public class IndexController {
    private final AppUserService appUserService;
    private final AppUserResource appUserResource;
    private final AccountService accountService;
    private final PaymentService paymentService;
    private final TransferService transferService;
    private final WithdrawService withdrawService;
    private final DepositService depositService;
    private final AccountRepository accountRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @GetMapping("/")
    public ModelAndView getIndex(){
        ModelAndView getIndexPage = new ModelAndView("index");
        getIndexPage.addObject("PageTitle", "Home");
        System.out.println("In Index Page Controller");
        return getIndexPage;
    }

    @GetMapping("/login")
    public ModelAndView getLogin(){
        ModelAndView getLoginPage = new ModelAndView("login");
        System.out.println("In Login Page Controller");
        getLoginPage.addObject("PageTitle", "Login");
        return getLoginPage;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(){
        ModelAndView getRegisterPage = new ModelAndView("register");
        System.out.println("In Register Page Controller");
        getRegisterPage.addObject("PageTitle", "Register");
        return getRegisterPage;
    }

    @GetMapping("/error")
    public ModelAndView getError(){
        ModelAndView getErrorPage = new ModelAndView("error");
        System.out.println("In Error Page Controller");
        getErrorPage.addObject("PageTitle", "Error");
//        getErrorPage.addObject("error","Not enough money / Bad account. Please try again");
        return getErrorPage;
    }

    @GetMapping("/dashboard")
    public ModelAndView getDashBoard(HttpSession session) {
        ModelAndView getDashboardPage = new ModelAndView("dashboard");
        System.out.println("In Dashboard Page Controller");
        getDashboardPage.addObject("PageTitle", "Dashboard");


        AppUser appUser = appUserService.findAppUserByUsername(appUserResource.getUsername());
        List<Account> accounts = accountService.findAllAcc(appUser);

        getDashboardPage.addObject("userAccounts", accounts);

        BigDecimal totalAccountsBalance;

        if (accounts.size() != 0) {

            totalAccountsBalance = accountRepository.getTotalBalance(appUser);
        }
        else{
            totalAccountsBalance = BigDecimal.valueOf(0);
        }
        totalAccountsBalance = totalAccountsBalance.setScale(2, RoundingMode.DOWN);
        getDashboardPage.addObject("totalBalance", totalAccountsBalance);


        getDashboardPage.addObject("appUser", appUser);

        return getDashboardPage;
    }

    @GetMapping("/dashboard/payment-history")
    public ModelAndView getPaymentHistory(HttpSession session) {
        ModelAndView getDashboardPage = new ModelAndView("paymentHistory");
        System.out.println("In Payment History Controller");
        getDashboardPage.addObject("PageTitle", "Payment History");


        AppUser appUser = appUserService.findAppUserByUsername(appUserResource.getUsername());
        List<Payment> payments = paymentService.findAllPayments(appUser);
        getDashboardPage.addObject("userPaymentsHistory", payments);
        getDashboardPage.addObject("appUser", appUser);

        return getDashboardPage;
    }

    @GetMapping("/dashboard/transaction-history")
    public ModelAndView getTransactionHistory(HttpSession session) {
        ModelAndView getDashboardPage = new ModelAndView("transactHistory");
        System.out.println("In Transaction History Controller");
        getDashboardPage.addObject("PageTitle", "Transaction History");


        AppUser appUser = appUserService.findAppUserByUsername(appUserResource.getUsername());

        List<TransactionHistory> transactionsHistory = transactionHistoryRepository.findAllTransactions(appUser);


        getDashboardPage.addObject("userTransactionsHistory", transactionsHistory);
        getDashboardPage.addObject("appUser", appUser);

        return getDashboardPage;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes){
        session.invalidate();
        redirectAttributes.addFlashAttribute("logged_out", "Logged out successfully");

        return "redirect:/login";
    }
}

