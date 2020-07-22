package com.jiwoong.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO implements UserDetails{
	
	
	private String id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String pass;

	@NotNull
	private String sex;// M or W (mysql은 check제약조건이 없음)

	@NotNull
	private String adress;
	
	@Email
	@NotNull
	private String email;

	@NotNull
	private String regidate;

	@NotNull
	private String role;//default 'role_member'
	private String birth;
	private String phone;
	
	//private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.toUpperCase()));
		return authorities;
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

}
