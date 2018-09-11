package com.jchen.util.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jchen.bean.User;
import com.jchen.service.UserService;
import com.jchen.util.jwt.JwtShiroToken;
import com.jchen.util.jwt.JwtUtil;

public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(AuthenticationToken token) {
		if (token instanceof JwtShiroToken)
			return true;
		if (token instanceof UsernamePasswordToken) 
			return true;
		return false;
	}

	/**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("+++++Check role and permission: " + principals.toString());
		String username = principals.toString();
		User user = userService.findUser(username);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole("admin");
		info.addStringPermission("read");
		return info;
	}

	/**
     * 	默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     *	检查方法有Jwt和普通User登入
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (token instanceof JwtShiroToken) {
			String jwtToken = (String) token.getCredentials();
			System.out.println("+++++Check token:" + jwtToken);
			if (!JwtUtil.verify(jwtToken)) {
				throw new AuthenticationException("++++ Shiro Jwt Token fail!");
			}
			return new SimpleAuthenticationInfo(JwtUtil.getUsername(jwtToken), jwtToken, getName());
		} else {
			UsernamePasswordToken userToken = (UsernamePasswordToken) token;
			String username = userToken.getUsername();
			String password = String.valueOf(userToken.getPassword());
			System.out.println("+++++Shiro login:" + username + "=" + password);
			if (!userService.loginUser(new User(username, password)))
				throw new AuthenticationException("++++ Shiro User login fail!");
			return new SimpleAuthenticationInfo(username, password, getName());
		}
	}

}
