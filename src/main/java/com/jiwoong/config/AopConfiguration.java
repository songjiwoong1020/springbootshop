package com.jiwoong.config;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.jiwoong.service.AdminService;

import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class AopConfiguration {
	
	private final AdminService adminService;
	
	@Pointcut("execution(* *UseAop(..))")
	public void navbarAOP() {}
	
	@AfterReturning(pointcut = "navbarAOP() && args(model, ..)")
	public void aopTest(Model model) throws Throwable{
		model.addAttribute("navbarLists", adminService.navbarTabsList());
	}
}
