package com.jchen.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.jchen.bean.Role;

@Mapper
public interface RoleMapper {
	
	@Select("CREATE TABLE role(id INTEGER PRIMARY KEY, roleId varchar(128), name varchar(128))")
	public void createRole();
	
	@Select("CREATE TABLE user_role(id INTEGER PRIMARY KEY, userId varchar(128), roleId varchar(128))")
	public void createUserRole();
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("INSERT INTO role(roleId, name) VALUES (#{objectId}, #{name})")
	public int insertRole(Role role);
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("INSERT INTO user_role(userId, roleId) VALUES (#{userId}, #{roleId})")
	public int insertUserRole(String userId, String roleId);
	
	@Delete("DELETE FROM role;")
	public void deleteAllRoles();
	
	@Delete("DELETE FROM user_role;")
	public void deleteAllUserRoles();
	
}
