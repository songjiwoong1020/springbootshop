package com.jiwoong.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.dto.NavbarTabsDTO;
import com.jiwoong.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {
	
	private final AdminMapper adminMapper;
	
	/**
	 * 멤버정보 조회
	 * @return
	 */
	public List<MemberDTO> allMember() {
		
		return adminMapper.allMember();
	}

	/**
	 * 네비게이션바에 들어있는 내용을 정렬되게 가지고있음.
	 * @return
	 */
	public List<List<NavbarTabsDTO>> navbarTabsList() {
		
		List<NavbarTabsDTO> tabsList = adminMapper.allNavbarTabs();
		
		List<List<NavbarTabsDTO>> list = new ArrayList<List<NavbarTabsDTO>>();
		
		for(NavbarTabsDTO tabList : tabsList) {
			List<NavbarTabsDTO> tabTypeList = adminMapper.nameNavbarTabs(tabList.getTab_name());
		
			list.add(tabTypeList);
		}
		
		return list;
	}
	
	/**
	 * 네비게이션바 단일 탭 생성
	 * @param tabName
	 * @param param
	 */
	public void saveSingleTab(String tabName, String param, String type) {
		int count = adminMapper.countNavbarTabs();
		int num = count + 1;
		
		adminMapper.saveSingleNavbarTabs(tabName, param, num, type);
	}

	/**
	 * 네비게이션바 다중 탭 생성
	 * @param tabName
	 * @param param
	 * @param type
	 * @param mName
	 */
	public void saveMultiTab(String tabName, String param, String type, String mName) {
		List<NavbarTabsDTO> tabs = adminMapper.allNavbarTabs();
		int num;
		if(!tabs.get(tabs.size() - 1).getTab_name().equals(tabName)) {
			num = tabs.size() + 1;
		}else {
			num = tabs.size();
		}
		adminMapper.saveMultiNavbarTabs(tabName, param, num, type, mName);
	}
}
