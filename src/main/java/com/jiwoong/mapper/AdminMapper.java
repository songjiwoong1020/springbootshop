package com.jiwoong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.dto.NavbarTabsDTO;

@Repository
@Mapper
public interface AdminMapper {
	
	public List<MemberDTO> allMember();
	
	public List<NavbarTabsDTO> allNavbarTabs();
	
	public List<NavbarTabsDTO> nameNavbarTabs(String name);

	public int countNavbarTabs();
	
	public int saveSingleNavbarTabs(String name, String param,  int num, String type);

	public int saveMultiNavbarTabs(String name, String param,  int num, String type, String mName);
}
