package com.jiwoong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiwoong.dto.TestDTO;
import com.jiwoong.mapper.TestDAOimpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestService {
	
	
	private final TestDAOimpl mapper;
	
	public int selectTest(){
		
		return mapper.selectTest();
	}

}
