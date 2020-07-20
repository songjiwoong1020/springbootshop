package com.jiwoong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecuirtyConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/admin/*").hasRole("ADMIN")
		.and()
			.csrf()//csrf에 대해서는 더 공부가 필요..
		.and()
			.httpBasic()
		.and()
			.formLogin()
		.and()
			.logout()
		.and()
			.exceptionHandling().accessDeniedPage("403"); //403페이지 처리
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/admin/login");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	}
	
	
	
	
}
