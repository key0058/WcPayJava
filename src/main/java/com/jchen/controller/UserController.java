package com.jchen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jchen.bean.User;
import com.jchen.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/login")
	public User login(@RequestParam String username, @RequestParam String password) {
		return userService.findUser(username, password);
	}
	
	@RequestMapping("/user/save")
	public int signUp(User user) {
		return userService.saveUser(user);
	}
	
	@RequestMapping("/user/all")
	public List<User> findUsers() {
		return userService.findAllUsers();
	}

}
