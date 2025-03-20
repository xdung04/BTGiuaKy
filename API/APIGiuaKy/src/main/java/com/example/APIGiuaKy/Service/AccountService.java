package com.example.APIGiuaKy.Service;

import com.example.APIGiuaKy.Entity.Account;
import com.example.APIGiuaKy.Repository.AccountRepository;
import com.example.APIGiuaKy.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account findByEmail(String email){
        return accountRepository.findByEmail(email);
    }
}
