package com.jchen.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jchen.bean.User;

@Controller
public class UserController {
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@PostMapping("/login")
	public String login(Model model, User user) {
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try {
			SecurityUtils.getSubject().login(token);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:401";
		}
		model.addAttribute("user", user);
		return "redirect:menu";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "index";
	}
	
	@RequestMapping("/401")
	public String response401() {
		return "401";
	}
	

}
