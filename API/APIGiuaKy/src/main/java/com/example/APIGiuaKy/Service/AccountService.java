
//22110376_Pham Nguyen Tien Manh
//22110378_Nguyen Duc Minh
package com.example.APIGiuaKy.Service;
import com.example.APIGiuaKy.DTO.AccountRequest;
import com.example.APIGiuaKy.Entity.Account;
import com.example.APIGiuaKy.Repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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

    public boolean createAccount(AccountRequest accountRequest) {
        if (checkExistingEmail(accountRequest.getEmail())) {
            return false;
        }
        Account account = Account.builder()
                .username(accountRequest.getUsername())
                .password(accountRequest.getPassword())
                .email(accountRequest.getEmail())
                .gender(accountRequest.getGender())
                .build();
        accountRepository.save(account);
        return true;
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
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email).orElse(null);
    }





}
