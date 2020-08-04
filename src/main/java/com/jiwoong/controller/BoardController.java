package com.jiwoong.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	/*
	@PostMapping("/board/summernoteImageUpload")
	@ResponseBody
	public Map<String, Object> summernoteImageUpload(HttpServletRequest request,
													@RequestParam("file") MultipartFile multipartFile) {
		
		Map<String, Object> object = new HashMap<String, Object>();

		String fileRoot = request.getSession().getServletContext().getRealPath("/summernoteImg");
		String originalFileName = multipartFile.getOriginalFilename();
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		String savedFileName = UUID.randomUUID() + extension;
		
		
		return object; 
	}
	*/
}
