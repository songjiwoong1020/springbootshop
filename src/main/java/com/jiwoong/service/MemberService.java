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
	//private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loadUserByUsername메소드 호출");
		MemberDTO memberDTO = memberMapper.findUser(username);
		
		return new User(memberDTO.getId(), memberDTO.getPass(), getAuthorities(memberDTO));
	}
	
	/**
	 * 권한 받아오는 부분
	 * @param memberDTO
	 * @return
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(MemberDTO memberDTO){
		String[] userRoles = convert(memberDTO.getRole());
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
	
	/**
	 * 실제 권한 매핑 함수
	 * @param list
	 * @return
	 */
	public String[] convert(List<MemberDTO> list) {
		Spring[] arrayOfString = new String[list.size()];
		
		int index = 0;
		for (MemberDTO memberDTO : list) {
			arrayOfString[index ++ ] = memberDTO
		}
	}
	
	
	
	
}