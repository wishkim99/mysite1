package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long no =Long.parseLong(request.getParameter("no"));
		//Long userNo =Long.parseLong(request.getParameter("userNo"));
	//	String password= request.getParameter("password");

		
		BoardVo vo = new BoardVo();
		vo.setNo(no);
//		vo.setUserNo(userNo);
//		vo.setPassword(password);                             
		
		new BoardDao().delete(vo);
		MvcUtil.redirect("/mysite02/board", request, response); //redirect해야됨

	}

}
