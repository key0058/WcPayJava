package com.jchen.util.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtil {
	
	private static final long EXPIRE_TIME = 60 * 1000;
	private static final String JWT_SECRET = "denni0616";
	
	public static String createToken(String username) {
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
	
	private static final String getUsername(String token) throws Exception {
		DecodedJWT jwt = JWT.decode(token);
		return jwt.getClaim("username").asString();
	}
	
	

}
