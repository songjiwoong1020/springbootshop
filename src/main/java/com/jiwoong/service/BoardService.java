package com.jiwoong.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jiwoong.dto.BoardDTO;
import com.jiwoong.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper boardMapper;
	
	public void list(Model model) {
		
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		
		String bname = (String) model.getAttribute("bname");
		

		int totalRecordCount = boardMapper.getTotalCount(bname);
		
		int pageSize = 5;//Integer.parseInt(request.getParameter("pageSize"));
		int blockPage = 2;//Integer.parseInt(request.getParameter("blockPage"));
		
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
		
		int nowPage = request.getParameter("nowPage") == null ? 1 : Integer.parseInt(request.getParameter("nowPage"));
		
		int start = (nowPage-1) * pageSize;
		int end = pageSize;
		
		List<BoardDTO> lists = boardMapper.list(bname, start, end);
		
		for(BoardDTO dto : lists) {
			String temp = dto.getContent().replace("\r\n", "<br/>");
			String sub = dto.getPostdate().substring(0, 10);
			dto.setContent(temp);
			dto.setPostdate(sub);
			
			System.out.println(dto.getId());
			System.out.println(dto.getName());
		}
		model.addAttribute("boardLists", lists);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("bname", bname);

	}
	
	public void write(Model model) {
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		
		String id = (String)model.getAttribute("id");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String bname = request.getParameter("bname");
		
		boardMapper.write(id, name, content, bname);
		
	}

}
