package com.example.demo.infrastructure.tool;

import cn.hutool.crypto.digest.BCrypt;

/**
 * @author 王景阳
 * @date 2023-03-27 18:45
 */
public class SecurityTool {
    public static String encoder(String rawPassword) {
        return BCrypt.hashpw(rawPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
