package com.jiwoong.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jiwoong.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ShopController {
	
	private final AdminService adminService;
	
	@GetMapping("/shop/{bname}")
	public String boardList(Model model, @PathVariable String bname, HttpServletRequest request) {	
	

		
		model.addAttribute("navbarLists", adminService.navbarTabsList());
		
		
		return "shop/shop";
	}

}
