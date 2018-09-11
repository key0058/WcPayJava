package com.jchen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jchen.bean.User;
import com.jchen.dao.UserBmobDao;
import com.jchen.mapper.RoleMapper;
import com.jchen.mapper.UserMapper;
import com.jchen.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserBmobDao userDao;
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public boolean loginUser(User user) {
		if (user == null) 
			return false;
		if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword()))
			return false;
		
		User bmobUser = findBmobUser(user.getUsername());
		if (bmobUser.getPassword().equals(user.getPassword())) {
			System.out.println("+++++ Service login success:" + user.getUsername() + "=" + user.getPassword());
			cleanUser(bmobUser);
			saveUser(bmobUser);
			return true;
		}
		return false;
	}
	
	@Override
	public void saveUser(User user) {
		if (user != null) {
			this.cleanUser(user);
			userMapper.insertUser(user);
			List<String> userRoles = userDao.findUserRoles(user.getUserId());
			for (String key : userRoles) {
				String[] values = key.split(",");
				roleMapper.insertUserRole(values[0], values[1]);
			}
		}
	}
	
	@Override
	public void cleanUser(User user) {
		if (user != null) {
			userMapper.deleteUser(user.getUserId());
			roleMapper.deleteUserRoles(user.getUserId());
		}
	}
	
	@Override
	public List<User> findAllUsers() {
		return userMapper.selectAllUsers();
	}
	
	@Override
	public User findUser(String username) {
		return userMapper.selectUser(username);
	}
	
	@Override
	public User findBmobUser(String username) {
		return userDao.findUser(username);
	}
}
