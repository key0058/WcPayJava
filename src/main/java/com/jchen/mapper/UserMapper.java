package com.jchen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.jchen.bean.User;

@Mapper
public interface UserMapper {
	
//	@Select("CREATE TABLE user(id INTEGER PRIMARY KEY, username varchar(32), password varchar(128), role varchar(20), permission varchar(128))")
//	public void createUserTable();

	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("INSERT INTO user(username, password, role) VALUES (#{username}, #{password}, #{role})")
	public int insertUser(User user);
	
	@Select("SELECT * FROM user")
	public List<User> findAllUsers();
}
