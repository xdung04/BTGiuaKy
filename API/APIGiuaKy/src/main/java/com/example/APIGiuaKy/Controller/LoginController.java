package com.example.APIGiuaKy.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.APIGiuaKy.DTO.LoginDTO;
import com.example.APIGiuaKy.DTO.LoginResponse;
import com.example.APIGiuaKy.Entity.Account;
import com.example.APIGiuaKy.Service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDTO loginInfo)
	{
		LoginResponse loginResponse = new LoginResponse();
		Optional<Account> account = loginService.findByEmailAndPassword(loginInfo.getEmail(), loginInfo.getPassword());
		if (account.isPresent())
		{
			loginResponse.setSuccess(true);
			loginResponse.setMesssage("Dang nhap thanh cong");
			return ResponseEntity.ok(loginResponse);
		}
		loginResponse.setSuccess(false);
		loginResponse.setMesssage("Dang nhap khong thanh cong");
		return ResponseEntity.badRequest().body(loginResponse);
	}
}
//22110446 - pham minh trung