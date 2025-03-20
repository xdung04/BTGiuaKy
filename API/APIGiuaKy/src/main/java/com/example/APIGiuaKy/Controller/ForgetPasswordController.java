//22110376_Pham Nguyen Tien Manh
package com.example.APIGiuaKy.Controller;
import com.example.APIGiuaKy.Service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal=true)
@RequestMapping("/shopcake/account")
public class ForgetPasswordController {
    AccountService accountService;

    @PostMapping("/forget-password/{email}/{newPassword}")
    public ResponseEntity<Boolean> updateAccount(@PathVariable String email, @PathVariable String newPassword) {
        boolean updated = accountService.updatePassword(email, newPassword);
        return ResponseEntity.ok(updated);
    }

}
