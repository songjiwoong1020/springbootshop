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
	
	@GetMapping("/board/{bname}/write")
	public String boardWrite(@PathVariable String bname, Model model, HttpServletRequest request) {
		
		model.addAttribute("banme", bname);
		
		model.addAttribute("navbarLists", adminService.navbarTabsList());
		
		
		return "board/write";
	}
	
	@PostMapping("/board/{bname}/writeAction")
	public String boardWriteAction(@PathVariable String bname, Model model, HttpServletRequest request, Principal principal) {
		
		String id = principal.getName();
		model.addAttribute("id", id);
		model.addAttribute("bname", bname);
		model.addAttribute("request", request);
		
		

		boardService.write(model);
		
		return "redirect:/board/" + bname;
	}
	
	@GetMapping("/board/{bname}/view")
	public String boardView(@PathVariable String bname, Model model, HttpServletRequest request) {
		
		model.addAttribute("request", request);
		
		boardService.view(model);
		
		
		return "board/view";
	}
	
	@GetMapping("/board/{bname}/delete")
	public String Delete(@PathVariable String bname, Model model, HttpServletRequest request ) {
		
		model.addAttribute("request", request);
		
		boardService.delete(model);
		
		return "redirect:/board/" + bname;
	}
	
	/**
	 * 파일 업로드 부분에서 굉장히 많이 삽질했다.
	 * 결과적으로 내가 하고싶은 방향은 s3를 사용하는것.(가장 현업과 가까운 방식인듯)
	 * 지금은 일단 프로젝트 내부에 올리는 방식으로 진행하자...
	 * 
	 * 
	 * 삽질 과정
	 * getRalPath를 통해 업로드를 진행할 폴더 경로를 입력해 주었었다.
	 * 그 결과 temp폴더로 이동되는데 이 임시 폴더가 계속 변하고 안에 내용물이 없는 상황이 발생.(스프링부트의 이슈?일 수도 있어보인다.)
	 * 여러가지 시도를 해본 결과 확실한 답은 찾지 못했으나 우선 파일 업로드에 대해 여러가지 가능하다는걸 알게되었다.
	 * 첫번째로 다른 서버에 올리는 방법(AWS S3가 대표적인듯 하다) 이 방법이 제일 괜찮아 보이지만 여기에 시간을 너무 많이써서 보류.
	 * 두번째로 DB에 직접 저장하는 방법이 있지만 현실적으로 불가능.
	 * 세번째로 서버가 파일을 직접 가지고 있는 방법이다. 이게 제일 보편적으로 사용되는 방법인것 같다.
	 * 와스(톰캣)에 올리는 방법이 getRealPath를 통해 저장되는 방법으로 보여지는데 그 이유는 모르겠다.
	 * 프로젝트 내부, 외부에도 올릴 수 있는데 각각 내부에 올릴시 많은 이슈가 발생한다고 한다. 외부에 올릴시에는 접근을 못하는 상황이 발생한다.(방법은 있겠지만..)
	 * 또, temp폴더에 관해서 알아보았는데 파일 업로드시 임시폴더가 생성되어서 임시폴더에 먼저 올라가는데에는 많은 이유가 있다고한다.
	 * 
	 * 나는 여기서 첫번째 방법을 채택할 계획이긴 하나 핑계지만 우선은 시간관계상.. 프로젝트를 EC2에 올릴때 같이 처리하겠다.. 비슷한 맥락일거라 생각해서..
	 * 그렇다면 우선은 다른 방법을 채택해야 한다. 나는 RDS를 이용하고있어서 각 로컬에서만 가능한 방법은 적합하지 않다. 가능은 하겠지만 매우 번거롭다.
	 * 깃을 통해 프로젝트를 진행하는 지금  채택 할 수 있는 방법은 프로젝트 내부에 파일을 업로드 시켜서 프로젝트 내부에서
	 * 왔다 갔다 가능하게 하는 방법이 현 시점에선 가장 나아보인다.
	 * 여기서 부터 또 문제. 이 문제는 스프링 부트의 문제인지 스프링 자체의 문제인지는 잘 모르겠으나 확실한건 내가 스프링에 대한 이해도가 너무 낮기에 확실한 해결을 못한다.
	 * 결론부터 말하면 src/main 하위에 webapp 폴더를 만들어 주면 프로젝트 내부에 접근이 가능하다.
	 * 그렇지 않으면 계속 temp폴더를 만들어서 그곳에 등록된다. 빈 temp폴더를 내 프로젝트와 맞게 구성을 맞춰줄까 생각 해 보았지만 맞는 방법같지 않다고 생각해서 포기..
	 * application.properties에 server.tomcat.basedir를 추가하면 temp폴더의 위치는 조정 해 줄 수는 있다.(basedir은 톰캣의 기초 디렉토리?인거 같다.)
	 * 결국 webapp폴더를 만들어 주면 해결 가능 한 문제이긴 하나.. thymeleaf를 사용해서 별도의 구성 없이 src/main/resources/templates에서 그냥 개발하는건줄 알았는데
	 * 결국 webapp폴더가 필요하다는게 영 찜찜할 따름이다. webapp폴더가 무슨 역할을 해주길래 폴더 하나 만들었다고 내부 접근이 가능해 지는걸까 싶다..
	 * 
	 * 
	 * @param request
	 * @param multipartFile
	 * @return
	 */
	@PostMapping("/board/summernoteImageUpload")
	@ResponseBody
	public Map<String, Object> summernoteImageUpload(HttpServletRequest request,
													@RequestParam("file") MultipartFile multipartFile) {
		
		//임시 메소드
		Map<String, Object> map = new HashMap<String, Object>();
		
		String fileRoot = request.getSession().getServletContext().getRealPath("/summernoteImg/");
		System.out.println("fileRoot=" + fileRoot);
		
		try {
			multipartFile.transferTo(new File(fileRoot + multipartFile.getOriginalFilename()));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String uploadFile = "/summernoteImg/" + multipartFile.getOriginalFilename();
		System.out.println("uploadFile=" + uploadFile);
		map.put("url", uploadFile);
		return map; 
	}
}
