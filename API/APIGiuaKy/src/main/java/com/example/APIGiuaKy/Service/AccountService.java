//22110376_Pham Nguyen Tien Manh
//22110378_Nguyen Duc Minh
package com.example.APIGiuaKy.Service;
import com.example.APIGiuaKy.Entity.Account;
import com.example.APIGiuaKy.Repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal=true)
@Service
public class AccountService {
    AccountRepository accountRepository;

    public boolean checkExistingEmail(String email) {
        if (accountRepository.findByEmail(email).isPresent()) {
            return true;
        }
        return false;
    }

    public boolean createAccount(String username, String email, String password, String gender) {
        try {
            // Kiểm tra xem email đã tồn tại chưa
            if (accountRepository.findByEmail(email).isPresent()) {
                return false;
            }

            Account account = new Account(username, email, password, gender);
            accountRepository.save(account);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updatePassword(String email, String newPassword) {
        try {
            Optional<Account> optionalAccount = accountRepository.findByEmail(email);
            if (optionalAccount.isPresent()) {
                Account account = optionalAccount.get();
                account.setPassword(newPassword);
                accountRepository.save(account);
                return true;
            }
            return false; // Không tìm thấy tài khoản
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }





}

