package com.jchen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jchen.bean.User;
import com.jchen.dao.UserDao;
import com.jchen.mapper.UserMapper;
import com.jchen.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int saveUser(User user) {
		return userMapper.insertUser(user);
	}
	
	@Override
	public List<User> findAllUsers() {
		return userMapper.findAllUsers();
	}
	
	@Override
	public User findUser(String username, String password) {
		return userDao.findUser(username, password);
	}
}
