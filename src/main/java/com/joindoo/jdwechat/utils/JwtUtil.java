package com.joindoo.jdwechat.utils;

import com.alibaba.fastjson.JSONObject;
import com.joindoo.jdwechat.cache.SessionModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

public class JwtUtil {
    final static String base64EncodedSecretKey = "base64EncodedSecretKey";//私钥


    //用户登录时间一小时
    final static long TOKEN_EXP = 1000 * 60*60;//过期时间,测试使用60秒

    //验证码存活时间 60s
    final static long VALICODE_PUBLICKEY_TOKEN_EXP = 1000 * 60;//过期时间,使用60秒

    public static String getToken(String userName,SessionModel sessionModel) {
        return Jwts.builder()
                .setSubject(userName)
                .claim("user", JSONObject.toJSONString(sessionModel))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    public static String getValicodePublicKeyToken(String valicodePublicKey,String valicode) {
        return Jwts.builder()
                .setSubject(valicodePublicKey)
                .claim("valicode", valicode)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + VALICODE_PUBLICKEY_TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }


    /**
     * @Date:17-12-12 下午6:21
     * @Author:root
     * @Desc:检查token,只要不正确就会抛出异常
     **/
    public static void checkToken(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }

    /**
     * 获取当前用户的角色id
     * @param token
     * @return
     * @throws ServletException
     */
    public static SessionModel getSessionModel(String token) throws ServletException {
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
            SessionModel sessionModel =JSONObject.parseObject(claims.get("user").toString(),SessionModel.class);
            return sessionModel;
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }

    /**
     * 获取验证码
     * @param valicodePublicKeyToken
     * @return
     * @throws ServletException
     */
    public static String getValicode(String valicodePublicKeyToken) throws ServletException {
        System.out.println("valicodePublicKeyToken = [" + valicodePublicKeyToken + "]");
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(valicodePublicKeyToken).getBody();
            String roles = claims.get("valicode").toString();
            return roles;
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }


    public static String getRefreshToken(String token) throws ServletException{
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();

            SessionModel sessionModel =JSONObject.parseObject(claims.get("user").toString(),SessionModel.class);

            String refreshToken = JwtUtil.getToken(sessionModel.getUserName(), sessionModel);
            return refreshToken;
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");
        } catch (Exception e) {
            throw new ServletException("other token exception");
        }
    }
}
