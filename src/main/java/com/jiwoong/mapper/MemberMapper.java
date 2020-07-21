package com.jiwoong.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jiwoong.dto.MemberDTO;

@Repository
@Mapper
public interface MemberMapper {
	
	public MemberDTO findUser(String username);

}
