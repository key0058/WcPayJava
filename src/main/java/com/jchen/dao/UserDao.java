package com.jchen.dao;

import java.util.List;

import com.jchen.bean.Permission;
import com.jchen.bean.Role;
import com.jchen.bean.User;

public interface UserDao {
	
	public User findUser(String username, String password);
	
	public List<Role> findUserRoles(String userId);
	
	public List<Permission> findPermissionsByRole(String roleId);

}
