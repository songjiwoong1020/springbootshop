package com.jiwoong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping("/")
	public String main() {
		return "/main";
	}
	
	@GetMapping("/member/login")
	public String memberLogin() {
		return "member/login";
	}
	
	@GetMapping("admin/login")
	public String adminLogin() {
		return "admin/login";
	}
}
