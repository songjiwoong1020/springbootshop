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

import com.jiwoong.security.MyLoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	//시큐리티에서 제공하는 암호화방식
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/member").permitAll()
			//.antMatchers("/board/**").hasAnyRole("MEMBER", "ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
		.and()
			.formLogin()
			.loginPage("/member/login")
			.defaultSuccessUrl("/")
			.failureUrl("/member/login?error")
			//.successHandler(new MyLoginSuccessHandler()) 미사용
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/")
		.and();
			//.cors().and().csrf().disable();
			//.exceptionHandling()
			//.accessDeniedPage("/member/denied"); //403페이지 처리
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		//위 항목은 인증 무시
		web.ignoring().antMatchers("/admin/login");
	}
}
