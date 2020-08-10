package com.jiwoong.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jiwoong.dto.BoardDTO;
import com.jiwoong.mapper.BoardMapper;
import com.jiwoong.util.EnvFileReader;
import com.jiwoong.util.PagingUtil;

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
		
		int pageSize = Integer.parseInt(EnvFileReader.getValue("SpringBbsInit.properties", 
				"springBoard.pageSize"));
		int blockPage = Integer.parseInt(EnvFileReader.getValue("SpringBbsInit.properties", 
				"springBoard.blockPage"));
		
		int totalPage = (int)Math.ceil((double)totalRecordCount/pageSize);
		
		int nowPage = request.getParameter("nowPage") == null ? 1 : Integer.parseInt(request.getParameter("nowPage"));
		
		int start = (nowPage-1) * pageSize;
		int end = pageSize;
		
		List<BoardDTO> lists = boardMapper.list(bname, start, end);
		
		String pagingImg = PagingUtil.pagingImg(totalRecordCount, pageSize, blockPage, nowPage,
					bname);
		
		int vNum = 0;
		int countNum = 0;
		for(BoardDTO dto : lists) {
			
			vNum = totalRecordCount - (((nowPage - 1) * pageSize) + countNum++);
			
			String temp = dto.getContent().replace("\r\n", "<br/>");
			String sub = dto.getPostdate().substring(0, 10);
			dto.setContent(temp);
			dto.setPostdate(sub);
			
			dto.setVNum(vNum);
			
		}
		model.addAttribute("pagingImg", pagingImg);
		model.addAttribute("boardLists", lists);


	}
	
	public void write(Model model) {
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		
		String id = (String)model.getAttribute("id");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		String bname = (String) model.getAttribute("bname");
		
			
		boardMapper.write(id, name, content, bname);
		
	}
	
	public void view(Model model) {
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		
		String idx = request.getParameter("idx");
			
		
		BoardDTO boardDTO = boardMapper.view(idx);
		
		model.addAttribute("boardDTO", boardDTO);
		
	}
	
	public void delete(Model model) {
		Map<String, Object> paramMap = model.asMap();
		HttpServletRequest request = (HttpServletRequest)paramMap.get("request");
		
		String idx = request.getParameter("idx");
		
		
		boardMapper.delete(idx);
		
		
		
	}

}
