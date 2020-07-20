package com.jiwoong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecuirtyConfiguration extends WebSecurityConfigurerAdapter{
	
	@Bean//시큐리티에서 제공하는 암호화방식
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/board/**").hasAnyRole("MEMBER", "ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
		//.and()
		//	.csrf()//csrf에 대해서는 더 공부가 필요..
		.and()
			.httpBasic()
		.and()
			.formLogin()
			.loginPage("member/login")
			.defaultSuccessUrl("/main")
			.permitAll()
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
			.logoutSuccessUrl("/main")
			.invalidateHttpSession(true)
		.and()
			.exceptionHandling().accessDeniedPage("/member/denied"); //403페이지 처리
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		//위 항목은 인증 무시
		web.ignoring().antMatchers("/admin/login");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}
	
	
	
	
}
