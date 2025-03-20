//22110376_Pham Nguyen Tien Manh
package com.example.APIGiuaKy.Repository;
import com.example.APIGiuaKy.Entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long> {
    Optional<OTP> findOTPByEmailAndIsUsedFalse (String email);
    Optional<OTP> findByEmail(String email);
}
