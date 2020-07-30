package com.jiwoong.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.dto.NavbarJoinDTO;
import com.jiwoong.dto.NavbarTabDTO;
import com.jiwoong.dto.NavbarTabTypeDTO;
import com.jiwoong.dto.NavbarTabsDTO;
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
	public String adminNavbar(Model model) {
		
	
		List<List<NavbarTabsDTO>> lists = adminService.navbarTabsList();
		
		
		model.addAttribute("lists", lists);
		model.addAttribute("creatMsg", "탭은 최대 10개까지 생성가능합니다. 현재" + lists.size() + "개의 탭이 생성되있습니다.");
		
		return "admin/navbar";
	}
	@PostMapping("/admin/navbar")
	public String adminNavbarPost(HttpServletRequest request) {
		
		String tabName = request.getParameter("tabName");
		String tabType = request.getParameter("tabType");
		
		if(tabType.equals("single")) {
			String param = request.getParameter("singleTabUrl");
			String singleType = request.getParameter("singleType");
			
	
			adminService.saveSingleTab(tabName, param);
			return "redirect:navbar";
			
		} else {
			System.out.println("멀티");
		}
		
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
