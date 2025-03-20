//22110376_Pham Nguyen Tien Manh
//22110378_Nguyen Duc Minh
package com.example.APIGiuaKy.Repository;

import com.example.APIGiuaKy.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);


}
