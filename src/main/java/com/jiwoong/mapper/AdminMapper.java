package com.jiwoong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jiwoong.dto.MemberDTO;
import com.jiwoong.dto.NavbarTabDTO;

@Repository
@Mapper
public interface AdminMapper {
	
	public List<MemberDTO> Allmember();
	
	public int navbarTabCount();

}
