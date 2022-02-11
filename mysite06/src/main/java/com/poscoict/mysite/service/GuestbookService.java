package com.poscoict.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.GuestbookRepository;
import com.poscoict.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public List<GuestbookVo> getMessageList() {
		return guestbookRepository.findAll();
	}
	
	public int deleteMessage(Long no, String password) {
		return guestbookRepository.delete(no, password);
	}
	
//	public Boolean deleteMessage(GuestbookVo vo) {
//		return guestbookRepository.delete(vo);
//	}
//	
	public int addMessage(GuestbookVo vo) {//vo가 insert한 다음에 no값이 셋팅됨
		return guestbookRepository.insert(vo);
	}
}