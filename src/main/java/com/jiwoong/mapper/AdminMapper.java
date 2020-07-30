package com.jiwoong.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.dto.NavbarJoinDTO;
import com.jiwoong.dto.NavbarTabDTO;
import com.jiwoong.dto.NavbarTabTypeDTO;
import com.jiwoong.dto.NavbarTabsDTO;

@Repository
@Mapper
public interface AdminMapper {
	
	public List<MemberDTO> allMember();
	
	public List<NavbarTabsDTO> allNavbarTabs();
	
	public List<NavbarTabsDTO> nameNavbarTabs(String name);

	public int countNavbarTabs();
	
	public int saveSingleNavbarTabs(String name, String param,  int num);
}
