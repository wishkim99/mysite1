package com.poscoict.mysite.mvc.guestbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.mysite.dao.GuestbookDao;
import com.poscoict.mysite.vo.GuestbookVo;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.util.MvcUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//List<GuestbookVo> list = new ArrayList<>();
		List<GuestbookVo> list = new GuestbookDao().findAll();
		request.setAttribute("list", list);
		MvcUtil.forward("guestbook/index", request, response);
	}

}