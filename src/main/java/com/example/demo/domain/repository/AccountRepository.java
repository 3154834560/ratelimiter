package com.example.demo.domain.repository;

import com.example.demo.domain.model.Account;
import org.springframework.stereotype.Repository;

/**
 * @author 王景阳
 * @date 2023-04-06 10:39
 */
@Repository
public interface AccountRepository {
    Account getById(Long id);

    boolean updateFund(Account account);
}
