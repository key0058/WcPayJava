package com.jchen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.jchen.bean.Permission;

@Mapper
public interface PermissionMapper {
	
	@Select("CREATE TABLE permission(id INTEGER PRIMARY KEY, permissionId varchar(128), operation varchar(128))")
	public void createPermission();
	
	@Select("CREATE TABLE role_permission(id INTEGER PRIMARY KEY, roleId varchar(128), permissionId varchar(128))")
	public void createRolePermission();
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("INSERT INTO permission(permissionId, operation) VALUES (#{objectId}, #{operation})")
	public int insertPermission(Permission permission);
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("INSERT INTO role_permission(roleId, permissionId) VALUES (#{roleId}, #{permissionId})")
	public int insertRolePermission(String roleId, String permissionId);
	
	@Select("SELECT p.* FROM permission p, role_permission rp WHERE rp.permissionId = p.permissionId AND rp.roleId = #{roleId}")
	public List<Permission> selectRolePermissions(String roleId);
	
	@Delete("DELETE FROM permission;")
	public void deleteAllPermissions();
	
	@Delete("DELETE FROM role_permission;")
	public void deleteAllRolePermissions();
}
