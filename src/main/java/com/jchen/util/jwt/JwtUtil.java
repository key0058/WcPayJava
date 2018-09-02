package com.jchen.util.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
	
	private static final long EXPIRE_TIME = 60 * 1000;
	private static final String JWT_SECRET = "denni0616";
	
	/**
     * 生成签名,5min后过期
     * @param username 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
	public static String sign(String username) {
		try {
			Date expire = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
			return JWT.create()
					.withClaim("username", username)
					.withExpiresAt(expire)
					.sign(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	/**
     * 校验token是否正确
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
	public static boolean verify(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
			JWTVerifier verifier = JWT.require(algorithm)
					.withClaim("username", getUsername(token))
					.build();
			verifier.verify(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
	public static final String getUsername(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("username").asString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
