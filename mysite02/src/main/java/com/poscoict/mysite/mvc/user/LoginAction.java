package com.poscoict.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.UserDao;
import com.poscoict.mysite.vo.UserVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		UserVo authUser=new UserDao().findByEmailAndPassword(email, password);
	
		if(authUser==null) {
			/*이메일 도는 비밀 번호가 틀림*/
			request.setAttribute("result", "fail");
			request.setAttribute("email", email); //자바에서 jsp로 보냄
			MvcUtil.forward("user/loginform", request, response);
			return ;
		}
		
		//인증 처리(Session 처리)
		HttpSession session=request.getSession(true); //있으면 주고 없으면 만듦
		session.setAttribute("authUser", authUser); //안건들이면 로그아웃될때까지 계속 유지
		
		MvcUtil.redirect(request.getContextPath(), request, response);
	}

}
