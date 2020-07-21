package com.jiwoong.service;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.mapper.MemberMapper;
import com.jiwoong.mapper.mybatisTest;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername메소드 호출");
		MemberDTO memberDTO = memberMapper.findUser(username);
		
		System.out.println("id=" + memberDTO.getUsername() + " pass=" + memberDTO.getPassword() + " role=" + memberDTO.getAuthorities());
		
		return new User(memberDTO.getUsername(), passwordEncoder.encode(memberDTO.getPassword()), memberDTO.getAuthorities());
	}
	
}