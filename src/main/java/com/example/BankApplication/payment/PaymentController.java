package com.example.BankApplication.payment;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping(path = "/payment")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public RedirectView doPayment(@RequestBody PaymentRequest request) throws AccountNotFoundException {
        return paymentService.payment(request);
    }

    @GetMapping
    public String paymentHistory(){
        return paymentService.showPaymentHistory();
    }
}
