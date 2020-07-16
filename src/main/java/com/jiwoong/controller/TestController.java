package com.jiwoong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	
	@RequestMapping("/")
	public String Hello(Model model) {
		
		model.addAttribute("test", "타임리프를 써보자");
		
		return "index";
	}
}
