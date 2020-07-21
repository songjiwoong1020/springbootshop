package com.jiwoong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/")
	public String main() {
		return "/main";
	}
	
	@GetMapping("/member/login")
	public String memberLoginForm() {
		System.out.println("loginForm진입");
		
		return "member/login";
	}

	@PostMapping("/member/login")
	public String memberLogin() {
		System.out.println("POST메소드 호출");
		
		return "member/login";//생각좀해보자
	}

	@GetMapping("/member/signup")
	public String memberSignUp() {
		return "member/signup";
	}
	
	@GetMapping("admin/login")
	public String adminLogin() {
		return "admin/login";
	}
}
