package com.jchen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jchen.bean.User;
import com.jchen.dao.UserDao;
import com.jchen.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public User findUser(String username, String password) {
		return userDao.findUser(username, password);
	}
}
