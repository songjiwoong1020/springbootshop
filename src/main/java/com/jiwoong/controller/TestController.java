package com.jiwoong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiwoong.dto.TestDTO;
import com.jiwoong.service.TestService;

import lombok.RequiredArgsConstructor;

@Controller
//@RequiredArgsConstructor
public class TestController {
	
	private final TestService testService;//@RequiredArgsConstructor를 쓰려면 private final이 되어야하는건가?
	
	//@Autowired를 사용하지 않고 생성자를 통한 주입. 을 하려고 했으나 어노테이션으로 해결 가능한부분이네요.
	//TestService testService;
	public TestController(TestService testService) {
		System.out.println("컨트룰러 생성자");
		this.testService = testService;
	}
	
	@RequestMapping("/")
	public String Hello(Model model) {
		System.out.println("컨트룰러진입");
		model.addAttribute("test", "타임리프를 써보자");
		int testList = testService.selectTest();

		
		model.addAttribute("list", testList);
		
		return "main";
	}
}
