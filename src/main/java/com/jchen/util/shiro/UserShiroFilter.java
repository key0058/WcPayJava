package com.jchen.util.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

public class UserShiroFilter extends BasicHttpAuthenticationFilter {
	
	@Override
	protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
		System.out.println("+++user shiro filter login attempt +++++");
		return true;
	}
	
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("+++user shiro filter excute login +++++");
		getSubject(request, response).login(null);
		return super.executeLogin(request, response);
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		try {
			System.out.println("+++user shiro filter access allow login +++++");
			executeLogin(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
