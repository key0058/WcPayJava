package com.jchen.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jchen.bean.Permission;
import com.jchen.bean.Role;
import com.jchen.bean.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Test
	public void testFunction() {
		User user = userMapper.selectUser("benchan");
		System.out.println(user.getUsername() + "=" + user.getUserId());
		
		for(Role role : user.getRoles()) {
			for (Permission pers : role.getPermissions()) {
				System.out.println(user.getUsername() + "=" + role.getName() + "=" + pers.getOperation());
			}
		}
		
//		List<Permission> pers = permissionMapper.selectRolePermissions("SEpi4447");
//		for (Permission permission : pers) {
//			System.out.println(permission.getOperation());
//		}
//		
//		Role role = roleMapper.selectRole("SEpi4447");
//		for (Permission permission : role.getPermissions()) {
//			System.out.println(role.getName() + "=" + permission.getOperation());
//		}
//		
//		List<Role> roles = roleMapper.selectUserRoles("JoF4NNNl");
//		for (Role r : roles) {
//			for (Permission p : r.getPermissions()) {
//				System.out.println(r.getName() + "=" + p.getOperation());
//			}
//		}
	}

}
