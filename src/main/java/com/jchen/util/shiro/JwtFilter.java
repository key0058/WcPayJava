package com.jchen.util.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

public class JwtFilter extends BasicHttpAuthenticationFilter {
	
	/**
	     * 判断用户是否想要登入。
	     * 检测header里面是否包含Authorization字段即可
     */
	@Override
	protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
		HttpServletRequest req = (HttpServletRequest) request;
		String authorization = req.getHeader("Authorization");
		return authorization != null;
	}
	
	/**
	 *  提交给realm进行登入，如果错误他会抛出异常并被捕获
	 *  如果没有抛出异常则代表登入成功，返回true
	 */
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		String authorization = req.getHeader("Authorization");
		JwtToken token = new JwtToken(authorization);
		getSubject(request, response).login(token);
		return true;
	}
	
	/**
     * 如果请求头带有 Authorization，则对 token 进行检查，否则直接通过
     * 如果请求头不存在 Authorization，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
     */
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (isLoginAttempt(request, response)) {
			try {
				executeLogin(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
     * 对跨域提供支持
     */
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		res.setHeader("Access-control-Allow-Origin", req.getHeader("Origin"));
		res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
		res.setHeader("Access-Control-Allow-Headers", req.getHeader("Access-Control-Request-Headers"));


		return super.preHandle(request, response);
	}

}
