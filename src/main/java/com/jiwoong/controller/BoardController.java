package com.jiwoong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jiwoong.service.AdminService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final AdminService adminService;

	@GetMapping("/board/{param}")
	public String board(Model model, @PathVariable String param) {
		model.addAttribute("lists", adminService.navbarTabsList());
		return "board/board";
	}
	
}
