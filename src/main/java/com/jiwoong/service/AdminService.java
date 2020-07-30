package com.jiwoong.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JTable.PrintMode;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.dto.NavbarJoinDTO;
import com.jiwoong.dto.NavbarTabDTO;
import com.jiwoong.dto.NavbarTabTypeDTO;
import com.jiwoong.dto.NavbarTabsDTO;
import com.jiwoong.mapper.AdminMapper;
import com.jiwoong.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	private final AdminMapper adminMapper;
	
	public List<MemberDTO> allMember() {
		
		return adminMapper.allMember();
	}

	public List<List<NavbarTabsDTO>> navbarTabsList() {
		
		List<NavbarTabsDTO> tabsList = adminMapper.allNavbarTabs();
		
		List<List<NavbarTabsDTO>> list = new ArrayList<List<NavbarTabsDTO>>();
		
		for(NavbarTabsDTO tabList : tabsList) {
			List<NavbarTabsDTO> tabTypeList = adminMapper.nameNavbarTabs(tabList.getTab_name());
		
			list.add(tabTypeList);
		}
		
		return list;
	}
	
	@Transactional
	public void saveSingleTab(String tabName, String param) {
		int count = adminMapper.countNavbarTabs();
		int num = count + 1;
		
		adminMapper.saveSingleNavbarTabs(tabName, param, num);
	}
}
