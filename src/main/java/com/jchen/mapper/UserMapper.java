package com.jchen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.jchen.bean.User;

@Mapper
public interface UserMapper {
	
	@Select("CREATE TABLE user(id INTEGER PRIMARY KEY, userId varchar(128), username varchar(128), password varchar(128))")
	public void createUser();

	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("INSERT INTO user(userId, username, password) VALUES (#{objectId}, #{username}, #{password})")
	public int insertUser(User user);
	
	@Select("SELECT * FROM user")
	public List<User> findAllUsers();
}
