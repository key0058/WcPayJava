package com.jchen.dao;

import java.util.List;
import java.util.Map;

import com.jchen.bean.Permission;
import com.jchen.bean.Role;
import com.jchen.bean.User;

public interface UserBmobDao {
	
	public User findUser(String username, String password);
	
	public List<Role> findRoles();
	
	public Map<String, String> findUserRoles();
	
	public List<Permission> findPermissions();
	
	public Map<String, String> findRolePermissions();

}
