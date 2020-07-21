package com.jiwoong.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO implements UserDetails{
	
	private String id;
	private String name;
	private String pass;
	private String sex;// M or W (mysql은 check제약조건이 없음)
	private String adress;
	private String email;
	private String regidate;
	private String sms;// yes or no
	private String role;//default 'member'
	private String birth;
	private String phone;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	@Override
	public String getPassword() {
		return this.pass;
	}
	@Override
	public String getUsername() {
		return this.id;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public enum Role{
		ROLE_MEMBER,
		ROLE_ADMIN
	}

}
