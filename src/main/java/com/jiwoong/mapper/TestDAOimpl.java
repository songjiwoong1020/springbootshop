package com.jiwoong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jiwoong.dto.TestDTO;

@Repository
@Mapper
public interface TestDAOimpl {
	
	int selectTest();

}
