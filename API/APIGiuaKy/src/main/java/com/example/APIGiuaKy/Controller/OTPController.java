//22110376_Pham Nguyen Tien Manh
package com.example.APIGiuaKy.Controller;
import com.example.APIGiuaKy.Service.OTPService;
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
@RequestMapping("shopcake/otp")
public class OTPController {
    OTPService otpService;

    // Tạo OTP và lưu vào cơ sở dữ liệu
    @PostMapping("/generate/{email}")
    public ResponseEntity<String> generateOTP(@PathVariable String email) {
        otpService.saveAndSendOTP(email);
        return ResponseEntity.ok("OTP has been generated");
    }

    // Xác thực OTP
    @PostMapping("/verifyOTP/{email}/{otp}")
    public ResponseEntity<Boolean> verifyOTP(@PathVariable String email, @PathVariable String otp) {
        boolean isVerified = otpService.verifyOTP(email, otp);
        return ResponseEntity.ok(isVerified);
    }

}
