package com.jiwoong.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.filechooser.FileSystemView;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jiwoong.service.AdminService;
import com.jiwoong.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final AdminService adminService;
	private final BoardService boardService;

	@GetMapping("/board/{bname}")
	public String boardList(Model model, @PathVariable String bname, HttpServletRequest request) {
		
		
		
		model.addAttribute("request", request);
		model.addAttribute("bname", bname);
		
		boardService.list(model);

		
		model.addAttribute("navbarLists", adminService.navbarTabsList());
		
		
		return "board/board";
	}
	
	@GetMapping("/board/write")
	public String boardWrite(Model model, HttpServletRequest request) {
		
		String bname = request.getParameter("bname");
		model.addAttribute("banme", bname);
		
		model.addAttribute("navbarLists", adminService.navbarTabsList());
		
		
		return "board/write";
	}
	
	@PostMapping("/board/writeAction")
	public String boardWriteAction(Model model, HttpServletRequest request, Principal principal) {
		
		String id = principal.getName();
		String bname = request.getParameter("bname");
		model.addAttribute("id", id);
		model.addAttribute("request", request);
		
		

		boardService.write(model);
		
		return "redirect:" + bname;
	}
	
	private final WebApplicationContext webApplicationContext;
	
	/**
	 * 스프링 부트의 경우 내장 톰캣을 사용하는데, 특별한 설정이 없으면 자동으로 temp폴더에 업로드가 진행된다.
	 * 
	 * @param request
	 * @param multipartFile
	 * @return
	 */
	@PostMapping("/board/summernoteImageUpload")
	@ResponseBody
	public Map<String, Object> summernoteImageUpload(HttpServletRequest request,
													@RequestParam("file") MultipartFile multipartFile) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String fileRoot = request.getSession().getServletContext().getRealPath("/");
//		String fileRoot = "/summernoteImg/";
		
		System.out.println("fileRoot=" + fileRoot);
		System.out.println("multipartFile.getName()=" + multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(new File(fileRoot + multipartFile.getOriginalFilename()));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String uploadFile = fileRoot + multipartFile.getOriginalFilename();
		System.out.println("uploadFile=" + uploadFile);
		
		map.put("url", uploadFile);
		return map; 
	}
	
}
