package cn.edu.cdtu.contactmanagement.util;

import cn.edu.cdtu.contactmanagement.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 * @Description JWT令牌生成
 * @Author makun
 * @Date 2023/5/22 15:18
 * @Version 1.0
 */
public class JWTUtil {
    /**
     * 主题，是由谁颁布的
     */
    private static final String SUBJECT = "online_class";
    /**
     * 过期时间，一周
     */
    private static final int EXPIRE = 60000 * 60 * 24 * 7;
    /**
     * 加密密钥
     */
    private static final String SECRET = "online_class";
    /**
     * 令牌前缀
     */
    private static final String TOKEN_PREFIX = "online_class";

    /**
     * 通过一个User对象生成一个JWT令牌
     * @param user
     * @return
     */
    public static String generateJWT(User user) {
        String token = Jwts.builder()
                // 设置主题
                .setSubject(SUBJECT)
                // 设置负载信息
                .claim("id",user.getId())
                .claim("username",user.getUsername())
                // 设置发布时间
                .setIssuedAt(new Date())
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                // 设置加密规则和密钥
                .signWith(SignatureAlgorithm.HS256,SECRET)
                // 生成令牌
                .compact();
        // 给令牌加上前缀（更安全）
        token = TOKEN_PREFIX + token;
        return token;
    }

    /**
     * 校验token令牌是否合法
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        try{
            return Jwts.parser()
                    // 填入密钥
                    .setSigningKey(SECRET)
                    // 替换令牌前缀
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                    // 获取负载信息
                    .getBody();
        }catch (Exception e){
            return null;
        }
    }
}
