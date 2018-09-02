package com.jchen.util.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.jchen.util.jwt.JwtUtil;

public class MyRealm extends AuthorizingRealm {
	
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	/**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = JwtUtil.getUsername(principals.toString());
		System.out.println("+++++Check role and permission: " + username);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole("admin");
		info.addStringPermission("read");
		return info;
	}

	/**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String jwtToken = (String) token.getCredentials();
		if (!JwtUtil.verify(jwtToken)) {
			throw new AuthenticationException("++++ Jwt Token fail!");
		}
		System.out.println("+++++Check token:" + jwtToken);
		return new SimpleAuthenticationInfo(jwtToken, jwtToken, "MY_REALM");
	}

}
