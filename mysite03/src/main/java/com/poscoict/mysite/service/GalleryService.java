package com.poscoict.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.poscoict.mysite.repository.GalleryRepository;
import com.poscoict.mysite.vo.GalleryVo;


@Service
public class GalleryService {
	@Autowired
	private GalleryRepository galleryRepository;

	public void saveImage(String url, String comments) {
		GalleryVo vo = new GalleryVo();
		vo.setComments(comments);
		vo.setUrl(url);
		galleryRepository.uploadImage(vo);
		
	}

	public List<GalleryVo> getImages(Model model) {
		List<GalleryVo> list = galleryRepository.getContents();
		model.addAttribute("list", list);
		return null;
	}

	public void removeImage(Long no) {
		galleryRepository.delectImage(no);
	}
}