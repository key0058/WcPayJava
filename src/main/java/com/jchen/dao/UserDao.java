package com.jchen.dao;

import com.jchen.bean.User;

public interface UserDao {
	
	public User findUser(String username, String password);

}
