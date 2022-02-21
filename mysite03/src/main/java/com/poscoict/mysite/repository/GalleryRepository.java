package com.poscoict.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {
	@Autowired
	private SqlSession sqlSession;

	public void uploadImage(GalleryVo vo) {
		sqlSession.insert("gallery.insert", vo);
	}

	public List<GalleryVo> getContents() {
		return sqlSession.selectList("gallery.getContent");
	}

	public boolean delectImage(Long no) {
		return sqlSession.delete("gallery.delect",no)==1;
	}
}