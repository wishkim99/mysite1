package com.poscoict.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.mysite.vo.BoardVo;


@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	
	public boolean insert
	(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo);
		return count == 1;
	}

	
	public List<BoardVo> findAll(String kwd) {
//		List<BoardVo> list = sqlSession.selectList("guestbook.findAll");
//		return list;
		return sqlSession.selectList("board.findAll", kwd);

	}

	public BoardVo findByNo(Long no){
		return sqlSession.selectOne("board.findByNo", no);
	}

//	public BoardVo findByNo(Long userNo) {
//		return sqlSession.selectOne("board.findByNo", userNo);
//	}

	public boolean delete(BoardVo vo) {
		int count = sqlSession.insert("board.delete", vo);
		return count == 1;
	
	}

//	public boolean update(Long no) {
//		boardVo vo = new boardVo();
//		vo.setNo(no);
//		return update(vo);	
//	}	
	
	public boolean update(BoardVo vo) {
		int count = sqlSession.insert("board.modify", vo);
		//System.out.println(vo);
		return count == 1;
	}



}
