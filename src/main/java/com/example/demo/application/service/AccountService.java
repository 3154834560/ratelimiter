package com.example.demo.application.service;

import com.example.demo.application.dto.input.account.TransferInput;
import com.example.demo.application.dto.output.account.TransferOutput;

/**
 * 账户服务接口
 *
 * @author 王景阳
 * @date 2023-04-08 9:57
 */
public interface AccountService {
    /**
     * 转账
     */
    TransferOutput transfer(TransferInput input);
}
