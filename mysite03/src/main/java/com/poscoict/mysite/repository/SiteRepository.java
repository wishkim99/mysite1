package com.poscoict.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.SiteVo;


@Repository
public class SiteRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean update(SiteVo siteVo) {
		int count = sqlSession.update("site.update", siteVo);
		return count == 1;
	}

	public SiteVo findByNo(Long no) {
		return sqlSession.selectOne("site.findByNo", no);
	}

	public SiteVo findAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("site.findAll");
	}
	
}