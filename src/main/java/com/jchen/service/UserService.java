package com.jchen.service;

import com.jchen.bean.User;

public interface UserService {
	
	public User findUser(String username, String password);
	
}
