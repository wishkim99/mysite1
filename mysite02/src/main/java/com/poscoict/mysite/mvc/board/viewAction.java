package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class viewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long no=Long.parseLong(request.getParameter("no"));
		//Long userNo=Long.parseLong(request.getParameter("userNo"));

		
//원래꺼
		BoardVo vo = new BoardDao().findByNo(no);
		vo.setNo(no);
		request.setAttribute("vo", vo);
	
		
//findByNo	
//		Long no=Long.parseLong(request.getParameter("no"));
//		BoardVo vo=new BoardDao().findByNo(no);
//		
//		request.setAttribute("vo", vo);

		
		//BoardVo vo=(BoardVo)request.getAttribute("list");
		MvcUtil.forward("board/view", request, response);
		
	}

}
