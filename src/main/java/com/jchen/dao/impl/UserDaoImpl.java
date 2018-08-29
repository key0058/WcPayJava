package com.jchen.dao.impl;

import org.springframework.stereotype.Repository;

import com.jchen.bean.User;
import com.jchen.dao.UserDao;
import com.jchen.util.bmob.Bmob;
import com.jchen.util.bmob.bson.BSONObject;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	
	@Override
	public User findUser(String username, String password) {
		Bmob.initBmob("37b79d1b03807693a777002779739d61", "3a4e7412d1d8822f077cd78c2449147a");
		BSONObject equalName = new BSONObject(Bmob.whereAll(username));
		BSONObject equalPwd = new BSONObject(Bmob.whereAll(password));
		BSONObject where = new BSONObject();
		where.put("username", equalName);
		where.put("password", equalPwd);
		
		String result = Bmob.find("Account", where.toString(), "username");
		System.out.println("====1=" + where.toString());
		System.out.println("====2=" + result);
		
		BSONObject object = new BSONObject(result);
		User user = new User();
//		user.setObjectId(object.getString("objectId"));
//		user.setUsername(object.getString("username"));
//		user.setPassword(object.getString("password"));
		return user;
	}

}
