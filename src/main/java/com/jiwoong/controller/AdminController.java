package com.jiwoong.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	private final AdminService adminService;
	
	@GetMapping("/admin/main")
	public String adminMain() {
		return "admin/main";
	}
	@GetMapping("/admin/member")
	public String adminMember(Model model) {
		
		model.addAttribute("lists", adminService.allMember());
		
		return "admin/member";
	}
	@GetMapping("/admin/navbar")
	public String adminNavbar() {
		
		return "admin/navbar";

	}
	@GetMapping("/admin/shop")
	public String adminShop() {
		
		return "admin/shop";
	}
	@GetMapping("/admin/board")
	public String adminBoard() {
		
		return "admin/board";
	}
}
