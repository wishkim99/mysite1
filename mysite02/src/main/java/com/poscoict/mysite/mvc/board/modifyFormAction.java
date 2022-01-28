package com.poscoict.mysite.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class modifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 접근제어(Access Control)
		// HttpSession session=request.getSession();
		// UserVo authUser=(UserVo)session.getAttribute("authUser");

		// UserVo vo=new UserDao().findByNo(authUser.getNo());

		// forwarding user/updateform
//				BoardVo vo = new BoardDao().findAll();
//				request.setAttribute("vo", vo);
		System.out.println("modifyFormAction");
		Long no = Long.parseLong(request.getParameter("no"));
		BoardVo vo = new BoardDao().findByNo(no);
		vo.setNo(no);
		request.setAttribute("vo", vo);

		MvcUtil.forward("/board/modify", request, response);
	}

}
