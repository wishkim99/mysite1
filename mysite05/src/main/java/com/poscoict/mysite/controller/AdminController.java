package com.poscoict.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.service.FileUploadService;
import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;


@Auth(role="ADMIN")//안붙이면 USER
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private SiteService siteService;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String main( Model model) {
		SiteVo siteVo = siteService.getSite(); //getSite로 정보 받고 siteVo를 메인에다 보냄
		System.out.println("================================" + siteVo);
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}

//	@Auth
//	@RequestMapping(value="admin/main/update", method=RequestMethod.GET)
//	public String update(@ModelAttribute @Valid SiteVo vo, Model model) {
//		SiteVo siteVo = siteService.getSite(vo.getNo());
//		model.addAttribute("siteVo", siteVo);
//		
//		return "admin/main";
//	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	//@Valid UserVo userVo에서 가운데 UserVo에 상관있음(객체 타입의 클래스 이름의 맨 앞을 소문자로만 해서 쓰면 됨)
	public String join(@ModelAttribute SiteVo vo, 
			@RequestParam(value="upload-file") MultipartFile multipartFile) { //join.jsp안에서 사용
		String url = fileUploadService.restore(multipartFile);
		vo.setProfile(url);
		if(siteService.update(vo)) {
			//ServletContext 객체는 모든 서블릿이 공유하는 객체
			servletContext.setAttribute("siteVo", vo); 
		}
		
		return "redirect:/admin";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}

}

