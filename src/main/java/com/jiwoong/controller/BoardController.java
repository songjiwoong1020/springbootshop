package com.jiwoong.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	
	@PostMapping("/board/summernoteImageUpload")
	@ResponseBody
	public JSONPObject summernoteImageUpload(HttpServletRequest request,
													@RequestParam("file") MultipartFile multipartFile) {
		
		//1.서버의 경로를 얻어온다.
		String fileRoot = request.getServletContext().getRealPath("/static/upload");
//		System.out.println(webApplicationContext.getServletContext().getRealPath("/")); 
//		System.out.println("context=" + request.getSession().getServletContext().getContextPath());
		System.out.println(request.getContextPath());
		System.out.println("fileRoot=" + fileRoot);
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("multipartFile.getName()=" + multipartFile.getName());
		try {
			//2.파일을 업로드 한다.
			multipartFile.transferTo(new File(fileRoot + multipartFile.getOriginalFilename()));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String uploadFile = fileRoot + multipartFile.getOriginalFilename();
		System.out.println("uploadFile=" + uploadFile);
		map.put("url", uploadFile);
		JSONPObject object = new JSONPObject("url", uploadFile);

		return object; 
	}
	
}
