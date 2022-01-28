package com.poscoict.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscoict.mysite.repository.BoardRepository;
import com.poscoict.mysite.vo.BoardVo;


@Service
public 
class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	// 새글, 답글 달기
	public boolean addContents(BoardVo boardVo) {
//		if(boardVo.getGroupNo() != null) {
//			boardRepository.updateOrderNo(boardVo.getGroupNo(), boardVo.getOrderNo());
//		}
		
		return boardRepository.insert(boardVo);
	}
	

	public List<BoardVo> getMessageList( String kwd
			//검색기능 구현(필수 파라미터)
			) {
		return boardRepository.findAll(kwd);
	}
	
	// 글보기
	public BoardVo getContents(Long no) {
		BoardVo boardVo = boardRepository.findByNo(no);
		
//		if(boardVo != null) {
//			boardRepository.updateHit(no);
//		}	
		return boardVo;
	}

	// 글 수정 하기 전,
	public BoardVo getContents(Long no, Long userNo){
		return null;
	}

	// 글 수정
	public Boolean updateContents(BoardVo vo) { //넘겨줌
		
		return boardRepository.update(vo);
		
	}

	// 글 삭제
	public Boolean deleteContents(BoardVo vo) {
		return boardRepository.delete(vo);
	}


	// 글 리스트(찾기결과)
	public  Map<String, Object> getContentsList(int currentPage, String keyword) {
		Map<String, Object> map = new HashMap<>();

		map.put("list", null);
		map.put("totalCount", 0);
		map.put("...", map);
	
		return map;
	}

	private boolean increaseGroupOrderNo(BoardVo vo){
		return false;
	}

	
}