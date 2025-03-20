//22110376_Pham Nguyen Tien Manh
package com.example.APIGiuaKy.Service;
import com.example.APIGiuaKy.Entity.OTP;
import com.example.APIGiuaKy.Repository.OTPRepository;
import com.example.APIGiuaKy.Util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OTPService {

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private EmailUtil emailUtil;

    public static String generateOtpCode() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public void saveAndSendOTP(String email) {
        String otpCode = generateOtpCode();
        emailUtil.sendOTP(email, otpCode);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expireTime = now.plus(5, ChronoUnit.MINUTES);
        Optional<OTP> existingOtp = otpRepository.findByEmail(email);
        OTP otp = new OTP();
        if (existingOtp.isPresent()) {
            otp = existingOtp.get();
            otp.setOtpCode(otpCode);
            otp.setCreatedAt(now);
            otp.setExpirationTime(expireTime);
            otp.setIsUsed(false);
            otpRepository.save(otp);
        }
        else {
            otp.setOtpCode(otpCode);
            otp.setEmail(email);
            otp.setCreatedAt(now);
            otp.setExpirationTime(expireTime);
            otp.setIsUsed(false);
            otpRepository.save(otp);
        }

    }


    public boolean verifyOTP(String email, String otp) {
        Optional<OTP> optionalOTP = otpRepository.findOTPByEmailAndIsUsedFalse(email);
        if (optionalOTP.isEmpty()) {
            return false; // Không tìm thấy OTP hoặc đã sử dụng
        }
        OTP otpEntity = optionalOTP.get();

        if (!otpEntity.getOtpCode().equals(otp)) {
            return false; // OTP không đúng
        }

        if (otpEntity.getExpirationTime().isBefore(LocalDateTime.now())) {
            return false; // OTP đã hết hạn
        }
        otpEntity.setIsUsed(true);
        otpRepository.save(otpEntity);
        return true;
    }



}

