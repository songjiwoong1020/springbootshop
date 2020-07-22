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
		return "member/login";
	}
	@GetMapping("/member/signup")
	public String memberSignUpForm() {
		return "member/signup";
	}
	@PostMapping("/member/signup")
	public String memberSignUp() {
		System.out.println("폼값 받자");
		return "member/signup";
	}
	
	@GetMapping("/admin/login")
	public String adminLogin() {
		return "admin/login";
	}
	@GetMapping("/admin/main")
	public String adminMain() {
		return "admin/main";
	}
	@GetMapping("/board/board")
	public String adminMain2() {
		return "board/board";
	}
	@GetMapping("/member/denied")
	public String memberDenied() {
		return "member/denied";
	}
}
