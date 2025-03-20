package com.example.APIGiuaKy.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.APIGiuaKy.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	Optional<Account> findByEmailAndPassword(String email, String password);
}
