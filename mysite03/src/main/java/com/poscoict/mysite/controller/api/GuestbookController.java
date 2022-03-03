package com.poscoict.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poscoict.mysite.dto.JsonResult;
import com.poscoict.mysite.service.GuestbookService;
import com.poscoict.mysite.vo.GuestbookVo;


@RestController("guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public JsonResult add(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		//보내는 역할
		vo.setNo(1L);
		vo.setPassword("");
		
		return JsonResult.success(vo);
	}

	@ResponseBody
	@RequestMapping(value="/list/{no}", method=RequestMethod.GET)
	public JsonResult list(@PathVariable(value="no", required=false) Long no) {
		List<GuestbookVo> list = guestbookService.getMessageList(no);
	//	model.addAttribute("list", list); //자바스크립트에서 처리하기때문에 굳이 필요 없음
		//return "guestbook/index";
		
		return JsonResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping("/delete/{no}")
	public JsonResult delete(
			@PathVariable("no")Long no,
			@RequestParam(value="password", required=true, defaultValue="")String password){//삭제해야될 데이터 내용
		guestbookService.deleteMessage(no, password);
		
		Long data=0L;
		
		//1. 삭제가 안된 경우(비밀번호가 틀린경우)
		data=-1L; //-1이 아닌경우 해당되는 li메시지 삭제하기
		
		//2. 삭제가 된 경우
		data=no;
		
		return JsonResult.success(data);
	}
}