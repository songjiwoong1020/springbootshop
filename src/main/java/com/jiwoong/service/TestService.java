package com.jiwoong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiwoong.dto.TestDTO;
import com.jiwoong.mapper.mybatisTest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
	
	
	private final mybatisTest mapper;
	
	public int selectTest(){
		
		return mapper.selectTest();
	}

}
