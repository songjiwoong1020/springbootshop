package com.jiwoong.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@GetMapping("/admin/navbarCtrl")
	public String adminNavbarCtrl(Model model) {
		
	
		List<List<NavbarTabsDTO>> lists = adminService.navbarTabsList();
		
		
		model.addAttribute("lists", lists);
		
		return "admin/navbar";
	}
	
	@GetMapping("/admin/navbarDisable")
	public String adminNavbarDisable(Model model) {
		
		
		List<List<NavbarTabsDTO>> lists = adminService.navbarTabsList();
		
		
		model.addAttribute("lists", lists);
		
		return "admin/navbar";
	}
	@PostMapping("/admin/navbarCtrl")
	public String adminNavbarPost(HttpServletRequest request) {
		
		String tabName = request.getParameter("tabName");
		String tabType = request.getParameter("tabType");
		
		if(tabType.equals("single")) {
			String param = request.getParameter("singleTabParam");
			String type = request.getParameter("singleBoardorShop");
	
			adminService.saveSingleTab(tabName, param, type);
			return "redirect:navbar";
			
		} else {
			int multiIndex = Integer.parseInt(request.getParameter("multiIndex"));
			for(int i=1; i<=multiIndex; i++) {
				String param = request.getParameter("multiTabParam" + i);
				String type = request.getParameter("multiBoardorShop" + i);
				String mName = request.getParameter("multiName" + i);
				adminService.saveMultiTab(tabName, param, type, mName);
			}
			return "redirect:navbar";
		}
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
