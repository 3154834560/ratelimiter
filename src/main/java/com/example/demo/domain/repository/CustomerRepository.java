package com.example.demo.domain.repository;

import com.example.demo.domain.model.Customer;
import org.springframework.stereotype.Repository;

/**
 * @author 王景阳
 * @date 2023-04-06 10:41
 */
@Repository
public interface CustomerRepository {
    Customer getById(Long id);
}
