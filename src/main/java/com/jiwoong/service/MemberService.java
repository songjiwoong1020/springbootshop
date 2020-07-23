package com.jiwoong.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.dto.MemberRequestDTO;
import com.jiwoong.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
	
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	/**
	 * UserDetailsService인터페이스 구현 후 오버라이딩.
	 * 시큐리티 사용을 위해 필요함. 유저 아이디로 로그인 처리.
	 * @parma username
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDTO memberDTO = memberMapper.findUser(username);
		
		System.out.println("id=" + memberDTO.getUsername() + " pass=" + memberDTO.getPassword() + " role=" + memberDTO.getAuthorities());
		
		return new User(memberDTO.getUsername(), memberDTO.getPassword(), memberDTO.getAuthorities());
	}
	
	/**
	 * 유효성검사 에러메세지를 저장하는 Map 반환.
	 * @param errors
	 * @return
	 */
	public Map<String, String> validateHandling(Errors errors){
		Map<String, String> validatorResult = new HashMap<>();
		
		for(FieldError error : errors.getFieldErrors()) {
			String validKeyName = String.format("valid_%s", error.getField());
			validatorResult.put(validKeyName, error.getDefaultMessage());
		}
		
		return validatorResult;
	}
	
	/**
	 * 회원가입을 위한 쿼리
	 * @param memberRequestDTO
	 */
	@Transactional
	public void saveMember(MemberRequestDTO memberRequestDTO) {
		//패스워드 암호화
		memberRequestDTO.setPass(passwordEncoder.encode(memberRequestDTO.getPass()));
		
		memberMapper.signUp(memberRequestDTO.toMemberDTO());
	}
}