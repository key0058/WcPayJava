package com.jchen.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequiresAuthentication
	@RequestMapping("/hello")
	public String hello(String name) {
		return "Hello " + name;
	}

}
