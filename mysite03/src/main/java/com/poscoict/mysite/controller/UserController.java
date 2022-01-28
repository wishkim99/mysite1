package com.poscoict.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.poscoict.mysite.security.Auth;
import com.poscoict.mysite.security.AuthUser;
import com.poscoict.mysite.service.UserService;
import com.poscoict.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo userVo) { // join.jsp안에서 사용
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() { // join.jsp안에서 사용
		return "/user/joinsuccess";
	}

	@RequestMapping(value="/login")
	public String login() {
		return "user/login";
	}
//	@RequestMapping(value = "/login", method = RequestMethod.GET) // GET으로 들어오면 그냥 form만 보여주면 됨
//	public String login() {
//		return "user/login";
//	}
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST) // login.jsp안에서 사용
//	public String login(
//			HttpSession session, //원래대로면 filter로 빠져야됨 
//			@RequestParam(value="email", required=true, defaultValue=" " )String email,
//			@RequestParam(value="password", required=true, defaultValue=" " )String password,
//			Model model) {
//		//authUser가 null이 아니면 login처리
//		UserVo authUser=userService.getUser(email, password); //email과 password를 던져줌
//	
//		if(authUser==null) {
//			model.addAttribute("result", "fail");
//			model.addAttribute("email", email); //email을 넘겨옴
//			return "user/login";
//		}
//		
	
//		/*인증처리*/
//		session.setAttribute("authUser", authUser);
//		return "redirect:/";
//	}
//	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) { 
		session.removeAttribute("authUser");
		session.invalidate(); //sessionId를 새걸로 바꿔줌
		return "redirect:/";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		Long userNo = authUser.getNo();
		UserVo userVo = userService.getUser(userNo);
		model.addAttribute("userVo", userVo);
		
		return "user/update";
	}
	
	@Auth
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo userVo) {
		userVo.setNo(authUser.getNo());
		userService.updateUser(userVo);
		
		return "redirect:/user/update";
	}
//	//@Auth(role="ADMIN") //annotation은 정보를 가지고 있음, 로그인이 되어있는지 아닌지 외부에서 확인
//	@RequestMapping(value="/update", method=RequestMethod.GET)
//	public String update(HttpSession session, Model model) { //보안처리
//		/*access controller*/ //@Auth있으면 체크 안해도 됨
//		UserVo authUser=(UserVo)session.getAttribute("authUser"); //authUser 꺼냄
//		if(authUser==null) {
//			return "redirect:/"; 
//		}
//		
//		Long userNo=authUser.getNo();
//		UserVo userVo=userService.getUser(userNo); //userVo로 끄집어냄(업데이트로 보내기위해)
//		model.addAttribute("userVo", userVo);
//		
//		return "user/update";
//	}
//	
//	@RequestMapping(value="/update", method=RequestMethod.POST)
//	public String update(HttpSession session, UserVo userVo) { //보안처리
//		/*access controller*/
//		UserVo authUser=(UserVo)session.getAttribute("authUser"); //authUser 꺼냄
//		if(authUser==null) {
//			return "redirect:/"; 
//		}
//	
//		userVo.setNo(authUser.getNo());
//		userService.updateUser(userVo);
//		//return "redirect:/user/update"; //get방식
//		authUser=userService.getUser(userVo.getNo()); //업데이트 시켜줌
//		session.setAttribute("authUser", authUser);
//		return  "redirect:/"; 
//		
//	}
	
//	@ExceptionHandler(Exception.class)
//	public String UserControllerExceptionHandler() {
//		return "error/exception";
//	}
	
	
}