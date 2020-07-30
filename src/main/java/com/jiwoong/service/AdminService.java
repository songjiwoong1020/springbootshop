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

	public int navbarTabCount() {
		
		return adminMapper.navbarTabCount();
	}
	/*
	public List<NavbarJoinDTO> joinNavbarTab() {
		
		List<NavbarJoinDTO> joins = adminMapper.joinNavbarTab();
		
		Map<String, Integer> map = new HashMap<>();
		for(NavbarJoinDTO join : joins) {
			Integer count = map.get(join.getName());
			map.put(join.getName(), (count == null) ? 1 : count +1);
		}
		
		Map<String, NavbarJoinDTO> result = new HashMap<>();
		
		
		for(NavbarJoinDTO join : joins) {
			if(map.get(join.getName()) == 1){
				System.out.println("단일");
				result.put("single", join);
			} else {
				System.out.println("다중");
				result.put("multi", join);
				
			}
		}
		return joins;
	}*/
	/*
	//정말 쓰레기만도 못한 로직이다..
	public Map<NavbarTabDTO, Object> navbarTabMap() {
		
		List<NavbarTabDTO> tabs = adminMapper.allNavbarTab();
		List<NavbarTabTypeDTO> tabTypes = adminMapper.allNavbarTabType();
		
		Map<NavbarTabDTO, Object> map = new HashMap<>();
		for(NavbarTabDTO tab : tabs) {
			
			List<NavbarTabTypeDTO> tabTypeList = new ArrayList<>();

			for(NavbarTabTypeDTO tabType : tabTypes) {
				if(tab.getType().equals("multi") && tab.getName().equals(tabType.getName())) {
					tabTypeList.add(tabType);
				}
			}
			map.put(tab, tabTypeList);
		}
		
		for(NavbarTabDTO key : map.keySet()) {
			System.out.println("key : " + key + " value : " + map.get(key));
			
		}
		
		return map;
		
	}
	*/
	public List<NavbarTabDTO> allNavbarTab(){
		
		return adminMapper.allNavbarTab();
	}
	
	public Map<String, List<NavbarTabTypeDTO>> test() {
		
		List<NavbarTabDTO> tabLists = adminMapper.allNavbarTab();
		
		Map<String, List<NavbarTabTypeDTO>> map = new HashMap<>();
		
		for(NavbarTabDTO tabList : tabLists) {
			List<NavbarTabTypeDTO> tabTypeList = adminMapper.nameNavbarTabType(tabList.getName());
			for(NavbarTabTypeDTO slal : tabTypeList) {
				System.out.println(slal.getM_Name());
			}
			map.put(tabList.getName(), tabTypeList);
		}
		
		for(NavbarTabDTO tabList : tabLists) {
			if(map.get(tabList.getName()).size() == 1) {
				System.out.println(tabList.getName() + "은 단일");
			} else {
				System.out.println(tabList.getName() + "은 다중");
				for(NavbarTabTypeDTO tlqkf : map.get(tabList.getName())) {
					System.out.println("name = " + tlqkf.getName());
					System.out.println("type = " + tlqkf.getType());
					System.out.println("url = " + tlqkf.getUrl());
					System.out.println("m_Name = " + tlqkf.getM_Name());
				}
			}
		}
		
		
		
		return map;
	}
	
	@Transactional
	public void saveSingleTab(String tabName,String tabType,String singleTabUrl,String singleType) {
		int count = navbarTabCount();
		int num = count + 1;
		
		adminMapper.saveNavbarTab(tabName, num, tabType);
		adminMapper.saveNavbarTabType(tabName, singleTabUrl, singleType);
		
	}
}
