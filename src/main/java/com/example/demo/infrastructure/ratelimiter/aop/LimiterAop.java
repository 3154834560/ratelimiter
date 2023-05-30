package com.example.demo.infrastructure.ratelimiter.aop;

import com.example.demo.infrastructure.exception.RateLimiterException;
import com.example.demo.infrastructure.ratelimiter.RateLimiter;
import com.example.demo.infrastructure.ratelimiter.annotations.Limiter;
import com.example.demo.infrastructure.rest.R;
import com.example.demo.infrastructure.tool.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 王景阳
 * @date 2023-04-07 20:32
 */
@Slf4j
@Aspect
@Component
public class LimiterAop {
    /**
     * 不同的接口，不同的流量控制
     * map的key为 Limiter.key
     */
    private final Map<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();
    private final Map<String, Lock> lockMap = new ConcurrentHashMap<>();

    @Around("@annotation(com.example.demo.infrastructure.ratelimiter.annotations.Limiter)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //拿limit的注解
        Limiter limiter = method.getAnnotation(Limiter.class);
        if (limiter != null) {
            Lock lock = getAndLock(limiter);
            boolean pass = false;
            if (lock.tryLock(limiter.tryTime(), limiter.timeUnit())) {
                RateLimiter rateLimiter = getAndAddRateLimiter(limiter);
                pass = rateLimiter.pass();
                lock.unlock();
            }
            // 验证是否通过
            if (!pass) {
                log.error("{}方法验证失败！", method.getName());
                this.responseFail(limiter.msg());
                return null;
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 获取并添加锁
     */
    private Lock getAndLock(Limiter limiter) {
        if (lockMap.containsKey(limiter.key())) {
            return lockMap.get(limiter.key());
        }
        Lock lock = new ReentrantLock();
        log.info("新建锁，key={}", limiter.key());
        lockMap.put(limiter.key(), lock);
        return lock;
    }

    /**
     * 获取并添加限速器
     */
    private RateLimiter getAndAddRateLimiter(Limiter limiter) {
        if (rateLimiterMap.containsKey(limiter.key())) {
            return rateLimiterMap.get(limiter.key());
        }
        RateLimiter rateLimiter = RateLimiter.getInstance(limiter.type(), limiter);
        log.info("新建{}限速，key={}", limiter.type().getType(), limiter.key());
        rateLimiterMap.put(limiter.key(), rateLimiter);
        return rateLimiter;
    }


    /**
     * 直接向前端抛出异常
     */
    private void responseFail(String msg) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = checkNull(servletRequestAttributes).getResponse();
        try {
            PrintWriter writer = checkNull(response).getWriter();
            writer.print(JsonTool.toJsonStr(R.fail(msg)));
        } catch (IOException e) {
            throw new RateLimiterException(e.getMessage(), e);
        }
    }

    private <T> T checkNull(T t) {
        return Optional.ofNullable(t).orElseThrow(() -> new RateLimiterException("限速器响应异常！"));
    }
}
