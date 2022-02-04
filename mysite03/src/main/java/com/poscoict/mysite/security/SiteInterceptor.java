package com.poscoict.mysite.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.service.SiteService;
import com.poscoict.mysite.vo.SiteVo;

public class SiteInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private SiteService siteService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { // 컨트롤러가 수행되기 전에 접근
		ServletContext sc = request.getServletContext();
		SiteVo siteVo = (SiteVo) sc.getAttribute("siteVo"); // siteVo이름의 객체를 불러옴

		siteVo = siteService.getSite(); // 비어있을때 db에서 값을 받아옴(필드값 다 넣어줌)
		sc.setAttribute("siteVo", siteVo); // siteVo 객체 셋팅
		// 6. 인증 확인!!!->controller의 handler(method)실행
		return true;
	}

}
