package com.example.APIGiuaKy.Controller;


import com.example.APIGiuaKy.Entity.Account;
import com.example.APIGiuaKy.Repository.AccountRepository;
import com.example.APIGiuaKy.Service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    AccountService accountService;


    @GetMapping("/api/account/{email}")
    public Account getAccountByEmail(@PathVariable String email) {
        return accountService.findByEmail(email);
    }
}
