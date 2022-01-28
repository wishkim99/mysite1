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

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*접근제어*/
		HttpSession session=request.getSession();
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null) {
			MvcUtil.redirect(request.getContextPath()+"/user?a=loginform", request, response);
		return;
		}
		Long no=Long.parseLong(request.getParameter("no"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");//jsp에서 던진걸 자바로
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		
		
		UserVo vo=new UserVo();
		vo.setName(name);
		vo.setEmail(email);  //받은걸 사용
		vo.setPassword(password); 
		vo.setGender(gender);
		vo.setNo(no);
		new UserDao().update(vo);
//		
		//redirct 안하면 계속 내가 이전에 요청했던 정보가 남아있음!! 
		session.setAttribute("authUser", vo); //수정된 vo가 반영
		MvcUtil.redirect(request.getContextPath(), request, response);

	}

}
