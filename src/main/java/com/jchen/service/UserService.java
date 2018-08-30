package com.jchen.service;

import java.util.List;

import com.jchen.bean.User;

public interface UserService {
	
	public int saveUser(User user);
	
	public List<User> findAllUsers();
	
	public User findUser(String username, String password);
	
	
	
}
