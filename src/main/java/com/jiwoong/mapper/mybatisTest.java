package com.jiwoong.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository//Repository의 역할은 DAO와 같다고 보면 될듯 하다
@Mapper
public interface mybatisTest {
	
	int selectTest();

}
