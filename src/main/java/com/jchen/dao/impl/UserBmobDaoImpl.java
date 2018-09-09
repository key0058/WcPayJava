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
import com.jchen.dao.UserBmobDao;
import com.jchen.util.bmob.Bmob;
import com.jchen.util.bmob.bson.BSONObject;

@Repository("userDao")
public class UserBmobDaoImpl implements UserBmobDao {
	
	@Value("${bmob.app-id}")
	private String BMOB_APP_ID;
	
	@Value("${bmob.rest-api-key}")
	private String BMOB_REST_API_KEY;
	
	@Override
	public User findUser(String username) {
		Bmob.initBmob(BMOB_APP_ID, BMOB_REST_API_KEY);
		String bql = "Select * FROM Account WHERE username = ?";
		String result = Bmob.findBQL(bql, username, true);
		
		System.out.println("++++ BMOB RESULT: " + result);
		
		BSONObject object = new BSONObject(result);
		Object[] objArray = (Object[]) object.get("results");
		if (objArray.length > 0) {
			User user = JSON.parseObject(String.valueOf(objArray[0]), new TypeReference<User>() {});
			user.setUserId(user.getObjectId());
			return user;
		}
		return null;
	}
	
	@Override
	public List<Role> findRoles() {
		List<Role> list = null;
		Bmob.initBmob(BMOB_APP_ID, BMOB_REST_API_KEY);
		String bql = "SELECT * FROM Role";
		String result = Bmob.findBQL(bql);
		
		System.out.println("++++ BMOB RESULT: " + result);
		
		BSONObject object = new BSONObject(result);
		Object[] objArray = (Object[])object.get("results");
		if (objArray.length > 0) {
			list = new ArrayList<Role>();
			for(int idx = 0; idx < objArray.length; idx++) {
				list.add(JSON.parseObject(String.valueOf(objArray[idx]), new TypeReference<Role>() {}));
			}
		}
		return list;
	}
	
	@Override
	public List<Permission> findPermissions() {
		List<Permission> list = null;
		Bmob.initBmob(BMOB_APP_ID, BMOB_REST_API_KEY);
		String bql = "SELECT * FROM Permission";
		String result = Bmob.findBQL(bql);
		
		System.out.println("++++ BMOB RESULT: " + result);
		
		BSONObject object = new BSONObject(result);
		Object[] objArray = (Object[])object.get("results");
		if (objArray.length > 0) {
			list = new ArrayList<Permission>();
			for(int idx = 0; idx < objArray.length; idx++) {
				list.add(JSON.parseObject(String.valueOf(objArray[idx]), new TypeReference<Permission>() {}));
			}
		}
		return list;
	}
	
	@Override
	public List<String> findUserRoles(String userId) {
		List<String> list = null;
		Bmob.initBmob(BMOB_APP_ID, BMOB_REST_API_KEY);
		String bql = "SELECT * FROM AccountRole WHERE userId = ?";
		String result = Bmob.findBQL(bql, userId, true);
		
		System.out.println("++++ BMOB RESULT: " + result);
		
		BSONObject object = new BSONObject(result);
		Object[] objArray = (Object[])object.get("results");
		if (objArray.length > 0) {
			list = new ArrayList<String>();
			for(int idx = 0; idx < objArray.length; idx++) {
				Map<String, String> values = JSON.parseObject(String.valueOf(objArray[idx]), new TypeReference<Map<String, String>>() {});
				list.add(values.get("accountId") + "," + values.get("roleId"));
			}
		}
		return list;
	}
	
	@Override
	public List<String> findRolePermissions() {
		List<String> list = null;
		Bmob.initBmob(BMOB_APP_ID, BMOB_REST_API_KEY);
		String bql = "SELECT * FROM RolePermission";
		String result = Bmob.findBQL(bql);
		
		System.out.println("++++ BMOB RESULT: " + result);
		
		BSONObject object = new BSONObject(result);
		Object[] objArray = (Object[])object.get("results");
		if (objArray.length > 0) {
			list = new ArrayList<String>();
			for(int idx = 0; idx < objArray.length; idx++) {
				Map<String, String> values = JSON.parseObject(String.valueOf(objArray[idx]), new TypeReference<Map<String, String>>() {});
				list.add(values.get("roleId") + "," + values.get("permissionId"));
			}
		}
		return list;
	}
}
