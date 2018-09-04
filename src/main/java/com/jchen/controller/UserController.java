package com.jchen.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/login")
	public MyResponse login(@RequestBody User user) {
		MyResponse res = new MyResponse();
		try {
			System.out.println(user.getUsername() + "==" + user.getPassword());
			user = userService.findUser(user.getUsername(), user.getPassword());
			if (user != null) {
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
	
	@RequiresAuthentication
	@RequestMapping("/user/save")
	public int signUp(User user) {
		return userService.saveUser(user);
	}
	
	@RequiresAuthentication
	@RequestMapping("/user/all")
	public List<User> findUsers() {
		return userService.findAllUsers();
	}
	
	@RequestMapping("/401")
	public MyResponse unauthorized() {
		MyResponse res = new MyResponse();
		res.setCode(MyResponseUtil.CODE_FAIL);
		res.setMessage(MyResponseUtil.MSG_FAIL_AUTH);
		return res;
	}

}
