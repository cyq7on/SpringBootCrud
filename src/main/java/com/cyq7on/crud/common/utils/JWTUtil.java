package com.cyq7on.crud.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTUtil implements Serializable {

    private static final String CLAIM_KEY_USER_ID = "user_id";
    private static final String USER_TYPE = "user_type";

    private static final String secret = "Account";
    // 过期时间30分钟
    private static final Long EXPIRE_TIME = 30 * 60 * 1000L;

    /**
     * 从token中获取userId
     * @param token
     * @return
     */
    public static Integer getUserId(String token) {
        Integer userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = (Integer) claims.get(CLAIM_KEY_USER_ID);
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }


    /**
     * 从token中获取用户角色分类
     * @param token
     * @return
     */
    public static Integer getUserType(String token) {
        Integer userId;
        try {
            final Claims claims = getClaimsFromToken(token);
            userId = (Integer) claims.get(USER_TYPE);
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }


    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }



    /**
     * 生成Token
     * @param userId
     * @param userType
     * @return
     */
    public static String generateToken(Integer userId, int userType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USER_ID, userId);
        claims.put(USER_TYPE, userType);
        return generateToken(claims);
    }


    /**
     * 生成token
     * @param claims
     * @return
     */
    private static String generateToken(Map<String, Object> claims) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + EXPIRE_TIME);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(exp)
                .compact();
    }

    public static boolean verificationToken(String token) {
        Integer userType = getUserType(token);
        return userType != null;
    }

}
