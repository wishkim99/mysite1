package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*접근제어*/
//		HttpSession session=request.getSession();
//		UserVo authUser=(UserVo)session.getAttribute("authUser");
//		if(authUser==null) {
//			MvcUtil.redirect(request.getContextPath()+"/user?a=loginform", request, response);
//		return;
//		}
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");

		Long userNo=Long.parseLong(request.getParameter("userNo"));
		
//		Long no=Long.parseLong(request.getParameter("no"));
//		Long userNo=Long.parseLong(request.getParameter("userNo"));
//		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUserNo(userNo);

		new BoardDao().insert(vo);
		//session.setAttribute("authUser", vo); //vo가 반영
		MvcUtil.redirect("/mysite02/board", request, response);
	}

}
