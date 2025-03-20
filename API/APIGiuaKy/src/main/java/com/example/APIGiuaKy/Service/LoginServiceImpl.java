package com.example.APIGiuaKy.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.APIGiuaKy.Entity.Account;
import com.example.APIGiuaKy.Repository.AccountRepository;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Optional<Account> findByEmailAndPassword(String email, String password) {
		return accountRepository.findByEmailAndPassword(email, password);
	}
	
	

}
//22110446 - pham minh trung