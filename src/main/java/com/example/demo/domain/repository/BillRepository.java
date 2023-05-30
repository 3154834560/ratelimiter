package com.example.demo.domain.repository;

import com.example.demo.domain.model.Bill;
import org.springframework.stereotype.Repository;

/**
 * @author 王景阳
 * @date 2023-04-06 10:40
 */
@Repository
public interface BillRepository {
    boolean save(Bill bill);
}
