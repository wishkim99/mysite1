package com.poscoict.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.SiteRepository;
import com.poscoict.mysite.vo.SiteVo;

@Service
public class SiteService {
	@Autowired
	private SiteRepository siteRepository;

	public boolean update(SiteVo siteVo) {
		return siteRepository.update(siteVo);
	}
	
	public SiteVo getSite() { //site에 대한 정보 보기
		// TODO Auto-generated method stub
		return siteRepository.findAll();
	}



}
