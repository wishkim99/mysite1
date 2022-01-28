package com.poscoict.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<GuestbookVo> findAll() {
//		List<GuestbookVo> list = sqlSession.selectList("guestbook.findAll");
//		return list;
		return sqlSession.selectList("guestbook.findAll");

	}

//	public Boolean delete(Long no, String password) {
//		GuestbookVo vo = new GuestbookVo();
//		vo.setNo(no);
//		vo.setPassword(password);
//		
//		return delete(vo);	
//	}	
	public int delete(Long no, String password) {
		Map<String, Object> map = new HashMap<>(); // object는 string, long다 받음
		map.put("no", no); // 이름 값,
		map.put("password", password);

		return sqlSession.delete("guestbook.delete", map);
	}

	public Boolean delete(GuestbookVo vo) {
		int count = sqlSession.insert("guestbook.delete", vo);
		return count == 1;

	}

	public int insert(GuestbookVo vo) {
		// 동적쿼리를 사용했기 때문에 한줄표현 가능
		return sqlSession.insert("guestbook.insert", vo);
	}

}