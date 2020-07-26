package com.jiwoong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/admin/login")
	public String adminLogin() {
		return "admin/login";
	}
	@GetMapping("/admin/main")
	public String adminMain() {
		return "admin/main";
	}
	@GetMapping("/admin/member")
	public String adminMember() {
		return "admin/member";
	}
	

}
