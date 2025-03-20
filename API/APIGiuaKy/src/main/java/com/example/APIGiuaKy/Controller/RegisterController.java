//22110376_Pham Nguyen Tien Manh
//22110378_Nguyen Duc Minh
package com.example.APIGiuaKy.Controller;

import com.example.APIGiuaKy.DTO.AccountRequest;
import com.example.APIGiuaKy.Entity.Account;
import com.example.APIGiuaKy.Service.AccountService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal=true)
@RequestMapping("/shopcake/account")
public class RegisterController {
    AccountService accountService;

    // Kiểm tra email đã tồn tại chưa
    @GetMapping("/checkmail/{email}")
    public ResponseEntity<Boolean> checkExistingEmail(@PathVariable String email) {
        boolean exists = accountService.checkExistingEmail(email);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> createAccount(@RequestBody AccountRequest accountRequest) {
        boolean created = accountService.createAccount(accountRequest);
        return ResponseEntity.ok(created);
    }


}
