package com.example.demo.infrastructure.tool;


import com.example.demo.domain.model.Customer;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 王景阳
 * @date 2023-03-27 16:22
 */
@Slf4j
public class TokenTool {
    /**
     * 密钥
     */
    private static final String SECRET_KEY = "wjy-shuzi-back";
    /**
     * 签名算法
     */
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    /**
     * 过期时间，单位毫秒
     */
    private static final long EXPIRATION = 2 * 60 * 1000;

    public static String generateToken(Customer customer) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(String.valueOf(customer.getId()))
                .setSubject(customer.getName())
                .setIssuedAt(DateTimeTool.getCurrentDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET_KEY)
                .setExpiration(DateTimeTool.getDateBy(EXPIRATION));
        return jwtBuilder.compact();
    }

    public static boolean verifyToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }
}
