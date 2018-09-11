package com.jchen.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jchen.bean.MyResponse;
import com.jchen.bean.User;
import com.jchen.service.UserService;
import com.jchen.util.MyResponseUtil;
import com.jchen.util.jwt.JwtUtil;

@RestController
public class JwtController {
	
	@Autowired
	private UserService userService;
	
	@RequiresAuthentication
	@RequestMapping("/hello")
	public String hello() {
		return "index";
	}
	
	@RequiresAuthentication
	@RequiresRoles("admin")
	@RequestMapping("/jwt/hello")
	public String jwtHello() {
		return "JWT index";
	}
	
	@PostMapping("/jwt/login")
	public MyResponse login(@RequestBody User user) {
		MyResponse res = new MyResponse();
		try {
			if (userService.loginUser(user)) {
				res.setCode(MyResponseUtil.CODE_SUCCESS);
				res.setMessage(MyResponseUtil.MSG_SUCCESS);
				res.setData(JwtUtil.sign(user.getUsername()));
			} else {
				res.setCode(MyResponseUtil.CODE_FAIL);
				res.setMessage(MyResponseUtil.MSG_FAIL_AUTH);
			}
			return res;
		} catch (Exception e) {
			res.setCode(MyResponseUtil.CODE_FAIL);
			res.setMessage(e.getMessage());
			return res;
		}
	}

}
