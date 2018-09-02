package com.jchen.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jchen.bean.User;
import com.jchen.dao.UserDao;
import com.jchen.util.bmob.Bmob;
import com.jchen.util.bmob.bson.BSONObject;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Value("${bmob.app-id}")
	private String BMOB_APP_ID;
	
	@Value("${bmob.rest-api-key}")
	private String BMOB_REST_API_KEY;
	
	@Override
	public User findUser(String username, String password) {
		Bmob.initBmob(BMOB_APP_ID, BMOB_REST_API_KEY);
		BSONObject equalName = new BSONObject(Bmob.whereAll(username));
		BSONObject equalPwd = new BSONObject(Bmob.whereAll(password));
		BSONObject where = new BSONObject();
		where.put("username", equalName);
		where.put("password", equalPwd);
		
		String result = Bmob.find("Account", where.toString(), "username");
		System.out.println("++++" + result);
		BSONObject object = new BSONObject(result);
		Object[] objArray = (Object[])object.get("results");
		User user = null;
		if (objArray.length > 0) {
			user = JSON.parseObject(String.valueOf(objArray[0]), new TypeReference<User>() {});
		}
		return user;
	}

}
