package com.poscoict.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.mysite.repository.UserRepository;
import com.poscoict.mysite.vo.UserVo;

@Service
public class UserService {//userService 안에서 레퍼지토리 사용할 것
	@Autowired
	private UserRepository userRepository;

	public void join(UserVo userVo) {
		userRepository.insert(userVo);
	}
	

	public UserVo getUser(String email, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByEmailAndPassword(email, password);
	}


	public UserVo getUser(Long userNo) {
		// TODO Auto-generated method stub
		return userRepository.findByNo(userNo);
	}


	public void updateUser(UserVo userVo) {
		// TODO Auto-generated method stub
		userRepository.update(userVo);
	}
}
