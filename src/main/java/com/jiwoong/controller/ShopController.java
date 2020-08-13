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
	
	
	@GetMapping("/shop/{bname}")
	public String boardListUseAop(Model model, @PathVariable String bname, HttpServletRequest request) {	

		
		
		return "shop/shop";
	}

}
