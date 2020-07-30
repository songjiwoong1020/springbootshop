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

@Repository
@Mapper
public interface AdminMapper {
	
	public List<MemberDTO> allMember();
	
	public int navbarTabCount();
	
	public List<NavbarTabDTO> allNavbarTab();
	
	public List<NavbarTabTypeDTO> allNavbarTabType();

	public List<NavbarTabTypeDTO> nameNavbarTabType(String name);
	
	public List<NavbarJoinDTO> joinNavbarTab();

	public int saveNavbarTab(@Param("_tabName") String tabName,
							@Param("_num") int num,
							@Param("_tabType") String tabType);

	public int saveNavbarTabType(@Param("_tabName") String tabName,
								@Param("_singleTabUrl") String singleTabUrl,
								@Param("_singleType") String singleType);
}
