package com.poscoict.mysite.mvc.guestbook;

import javax.servlet.RequestDispatcher;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("deleteform".equals(actionName)) { //deleteform.jsp로 보냄
			System.out.println("delet");
			action=new DeleteFormAction();
			
		} else if("delete".equals(actionName)) { //삭제 구현
			action=new DeleteAction();
			
		} else if("insert".equals(actionName)) {
			action=new AddAction();
		} else {
			action = new IndexAction();
		}
		
		return action;
	}

}