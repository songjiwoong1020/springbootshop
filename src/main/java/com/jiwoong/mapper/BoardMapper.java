package com.jiwoong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.jiwoong.dto.BoardDTO;

@Mapper
@Repository
public interface BoardMapper {
	
	public int getTotalCount(String bname);
	
	public List<BoardDTO> list(String bname, int start, int end);
	
	public int write(String id, String name, String content, String bname);

	public BoardDTO view(String idx);

	public void delete(String idx);

}
