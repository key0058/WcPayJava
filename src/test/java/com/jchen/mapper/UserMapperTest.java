package com.jchen.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jchen.bean.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void testFunction() {
		User user = new User();
		user.setUsername("Cenxx");
		user.setPassword(String.valueOf(Math.random()));
			
		
		List<User> list = userMapper.findAllUsers();
		for (User u: list) {
			System.out.println(u.getUsername() + "=" + u.getPassword());
		}
	}

}
