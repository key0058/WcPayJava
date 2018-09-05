package com.jchen.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jchen.bean.Permission;
import com.jchen.bean.Role;
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
//		BSONObject equalName = new BSONObject(Bmob.whereAll(username));
//		BSONObject equalPwd = new BSONObject(Bmob.whereAll(password));
//		BSONObject where = new BSONObject();
//		where.put("username", equalName);
//		where.put("password", equalPwd);
//		String result = Bmob.find("Account", where.toString(), "username");
		
		String bql = "Select * from Account where username = ? and password = ?";
		String value = username + "," + password;
		String result = Bmob.findBQL(bql, value);
		
		System.out.println("++++" + result);
		BSONObject object = new BSONObject(result);
		Object[] objArray = (Object[])object.get("results");
		User user = null;
		if (objArray.length > 0) {
			user = JSON.parseObject(String.valueOf(objArray[0]), new TypeReference<User>() {});
			
		}
		return user;
	}
	
	@Override
	public List<Role> findUserRoles(String userId) {
		List<Role> list = new ArrayList<Role>();
		Bmob.initBmob(BMOB_APP_ID, BMOB_REST_API_KEY);
		String bql = "SELECT roleId from AccountRole where accountId = ?";
		String result = Bmob.findBQL(bql, userId);
		BSONObject object = new BSONObject(result);
		Object[] objArray = (Object[])object.get("results");
		List<String> values = new ArrayList<String>();
		for (int idx = 0; idx < objArray.length; idx++) {
			Map map = JSON.parseObject(String.valueOf(objArray[idx]), new TypeReference<Map>() {});
			values.add("\"" + map.get("roleId") + "\"");
		}
		
		bql = "Select * from Role where objectId in (?)";
		result = Bmob.findBQL(bql, String.join(",", values));
		object = new BSONObject(result);
		objArray = (Object[])object.get("results");
		System.out.println("+++++" + result);
		
		for (int idx = 0; idx < objArray.length; idx++) {
			Role role = JSON.parseObject(String.valueOf(objArray[idx]), new TypeReference<Role>() {});
			list.add(role);
		}
		return list;
	}
	
	@Override
	public List<Permission> findPermissionsByRole(String roleId) {
		return null;
	}

}
