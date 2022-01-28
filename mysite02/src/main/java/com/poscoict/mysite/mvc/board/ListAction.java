package com.poscoict.mysite.mvc.board;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.BoardDao;
import com.poscoict.mysite.vo.BoardVo;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int pageCount = 10;
//		int currentPage = 2;
//		int nextPage = -1;
//		int startPage = 3;
//		int prePage = 2;
//		Map<K, V> m;
//		m.put(response, m);
		
		String kwd=request.getParameter("kwd");
		List<BoardVo> list = new BoardDao().findAll(kwd);
		request.setAttribute("list", list);
		

		MvcUtil.forward("/board/list", request, response);
	}

}
