package com.jchen.service;

import java.util.List;

import com.jchen.bean.User;

public interface UserService {
	
	public boolean loginUser(User user);
	
	public void cleanUser(User user);
	
	public void saveUser(User user);
	
	public List<User> findAllUsers();
	
	public User findUser(String username);
	
	public User findBmobUser(String username);
	
	
	
}
