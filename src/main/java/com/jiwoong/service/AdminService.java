package com.jiwoong.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.mapper.AdminMapper;
import com.jiwoong.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	private final AdminMapper adminMapper;
	
	public List<MemberDTO> allMember() {
		
		return adminMapper.Allmember();
	}

	public int navbarTabCount() {
		
		return adminMapper.navbarTabCount();
	}
	
	
	
	
}
