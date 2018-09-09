package com.jchen.util.jwt;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtShiroToken implements AuthenticationToken {
	
	private String token;
	
	public JwtShiroToken(String token) {
		this.token = token;
	}
	
	@Override
	public Object getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}
