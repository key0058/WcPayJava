package com.jchen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.jchen.bean.User;

@Mapper
public interface UserMapper {
	
	@Select("CREATE TABLE user(id INTEGER PRIMARY KEY, userId varchar(128), username varchar(128), password varchar(128))")
	public void createUser();

	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("INSERT INTO user(userId, username, password) VALUES (#{objectId}, #{username}, #{password})")
	public int insertUser(User user);
	
	@Select("SELECT * FROM user WHERE username = #{username}")
	@Results({
		@Result(property = "roles", column = "userId", many = @Many(select = "com.jchen.mapper.RoleMapper.selectUserRoles")),
		@Result(property = "userId", column = "userId")
	})
	public User selectUser(String username);

	@Select("SELECT * FROM user")
	public List<User> selectAllUsers();
	
	@Delete("DELETE FROM user WHERE userId = #{userId}")
	public void deleteUser(String userId);
	
	@Delete("DELETE FROM user")
	public void deleteAllUsers();
}
