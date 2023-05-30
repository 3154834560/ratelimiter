package com.example.demo.api;

import com.example.demo.application.dto.input.account.TransferInput;
import com.example.demo.application.dto.output.account.TransferOutput;
import com.example.demo.application.service.AccountService;
import com.example.demo.infrastructure.ratelimiter.annotations.Limiter;
import com.example.demo.infrastructure.ratelimiter.enums.RateLimiterType;
import com.example.demo.infrastructure.rest.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 账户接口
 *
 * @author 王景阳
 * @date 2023-04-05 22:30
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/account")
public class AccountApi {
    private final AccountService service;

    /**
     * 转账
     */
    @Limiter(key = "1", type = RateLimiterType.token_bucket)
    @PutMapping("/transfer")
    public R<TransferOutput> transfer(@Valid @RequestBody TransferInput input) {
        return R.data(service.transfer(input));
    }
}