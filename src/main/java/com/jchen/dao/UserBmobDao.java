package com.jchen.dao;

import java.util.List;

import com.jchen.bean.Permission;
import com.jchen.bean.Role;
import com.jchen.bean.User;

public interface UserBmobDao {
	
	public User findUser(String username);
	
	public List<Role> findRoles();
	
	public List<Permission> findPermissions();
	
	public List<String> findUserRoles(String userId);
	
	public List<String> findRolePermissions();

}
