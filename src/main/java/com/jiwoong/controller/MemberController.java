package com.jiwoong.controller;

import java.util.List;
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
import com.jiwoong.dto.NavbarTabsDTO;
import com.jiwoong.service.AdminService;
import com.jiwoong.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final AdminService adminService;
	
	@GetMapping("/")
	public String main(Model model) {
		
		List<List<NavbarTabsDTO>> lists = adminService.navbarTabsList();
		model.addAttribute("lists", lists);
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
	public String memberSignUp(@Valid MemberRequestDTO memberRequestDTO, Errors errors, Model model) {
		
		//회원가입 유효성 검사를 통과하지 못했을때..
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
	

	@GetMapping("/board/board")
	public String board() {
		return "board/board";
	}
	@GetMapping("/member/denied")
	public String memberDenied() {
		return "member/denied";
	}
}
