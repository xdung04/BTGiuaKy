package com.example.APIGiuaKy.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.APIGiuaKy.Entity.Account;

@Service
public interface LoginService {

	Optional<Account> findByEmailAndPassword(String email, String password);

}
