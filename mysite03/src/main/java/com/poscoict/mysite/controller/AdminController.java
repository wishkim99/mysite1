package com.poscoict.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poscoict.mysite.security.Auth;

//@Auth //(role="ADMIN")//안붙이면 USER
@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping("")
	public String main() {
		return "admin/main";
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
