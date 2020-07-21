package com.jiwoong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecuirtyConfiguration extends WebSecurityConfigurerAdapter{
	
	//시큐리티에서 제공하는 암호화방식
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("시큐리티 컨피규레이션");
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/board/**").hasAnyRole("MEMBER", "ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
		//.and()
		//	.csrf()//csrf에 대해서는 더 공부가 필요..
		//.and()
		//	.httpBasic()
		.and()
			.formLogin()
			.loginPage("/member/login")
			.defaultSuccessUrl("/")
			.failureUrl("/member/login?error")
			.permitAll()
		.and()
			.logout()
			.logoutUrl("/member/login?logout")
			.logoutSuccessUrl("/")
		.and();
			//.exceptionHandling()
			//.accessDeniedPage("/member/denied"); //403페이지 처리
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		//위 항목은 인증 무시
		web.ignoring().antMatchers("/admin/login");
	}
}
