package com.jchen.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jchen.bean.Permission;
import com.jchen.bean.Role;
import com.jchen.dao.UserBmobDao;
import com.jchen.mapper.PermissionMapper;
import com.jchen.mapper.RoleMapper;
import com.jchen.mapper.UserMapper;
import com.jchen.service.DbService;

@Service("dbService")
public class DbServiceImpl implements DbService {
	
	@Autowired
	private UserBmobDao userDao;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public void initDB() {
		
//		userMapper.createUser();
//		roleMapper.createRole();
//		roleMapper.createUserRole();
//		permissionMapper.createPermission();
//		permissionMapper.createRolePermission();
		
//		userMapper.deleteAllUsers();
		roleMapper.deleteAllRoles();
		roleMapper.deleteAllUserRoles();
		permissionMapper.deleteAllPermissions();
		permissionMapper.deleteAllRolePermissions();
		
		List<Role> roles = userDao.findRoles();
		for (Role role : roles) {
			roleMapper.insertRole(role);
		}
		
		List<Permission> permissions = userDao.findPermissions();
		for (Permission permission : permissions) {
			permissionMapper.insertPermission(permission);
		}
		
		List<String> rolePermissions = userDao.findRolePermissions();
		for (String key : rolePermissions) {
			String[] values = key.split(",");
			permissionMapper.insertRolePermission(values[0], values[1]);
		}
		
	}
}
