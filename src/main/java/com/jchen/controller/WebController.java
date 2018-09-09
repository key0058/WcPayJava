package com.jchen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jchen.bean.User;
import com.jchen.service.UserService;

@Controller
public class WebController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login(Model model) {
		User user = userService.findBmobUser("benchan");
		model.addAttribute(user);
		return "menu";
	}

}
