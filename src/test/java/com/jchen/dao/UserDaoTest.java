package com.jchen.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	
	@Autowired
	private UserBmobDao userDao;
	
	
	@Test
	public void testFunction() {
		userDao.findUser("benchan");
//		userDao.findRoles();
//		userDao.findPermissions();
//		userDao.findUserRoles();
//		userDao.findRolePermissions();
	}

}
