package com.example.demo.config.interceptor;

import com.example.demo.infrastructure.rest.R;
import com.example.demo.infrastructure.tool.JsonTool;
import com.example.demo.infrastructure.tool.TokenTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 王景阳
 * @date 2023-03-04 19:49
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return verifyToken(request, response);
    }

    private boolean verifyToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        if (TokenTool.verifyToken(token)) {
            return true;
        }
        try {
            PrintWriter writer = response.getWriter();
            writer.print(JsonTool.toJsonStr(R.fail()));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
