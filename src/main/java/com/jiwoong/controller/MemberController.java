package com.jiwoong.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.dto.MemberRequestDTO;
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
		System.out.println("1단계 : 회원가입 폼 진입");
		return "member/signup";
	}
	@PostMapping("/member/signup")
	public String memberSignUp(@Valid MemberRequestDTO memberRequestDTO, Errors errors, Model model) {
		
		if(errors.hasErrors()) {
			model.addAttribute("memberRequestDTO", memberRequestDTO);
			
			Map<String, String> validatorResult = memberService.validateHandling(errors);
			for(String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}
			return "member/signup";
		}
		
		memberService.saveMember(memberRequestDTO);
		return "redirect:/";
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
