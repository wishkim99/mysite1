package com.poscoict.mysite.mvc.user;

import com.poscoict.mysite.mvc.main.MainAction;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action=null;
		if("joinform".equals(actionName)) {
			action=new JoinFormAction();
			
		}else if("join".equals(actionName)){
			action=new JoinAction();
		}else if("joinsuccess".equals(actionName)){
			action=new JoinSuccessAction();
		}else if("loginform".equals(actionName)){
			action=new LoginFormAction();
		}else if("login".equals(actionName)){
			action=new LoginAction();
		}else if("logout".equals(actionName)){
			action=new LogoutAction();
		}else if("updateform".equals(actionName)){ //로그인을 꼭 해야만 들어갈 수 있는 활동(접근제어를 해야함!)
			action=new UpdateFormAction();
		}else if("update".equals(actionName)){ //로그인을 꼭 해야만 들어갈 수 있는 활동(접근제어를 해야함!)
			action=new UpdateAction();
		}
		else {
			action=new MainAction();
		}
		return action;
	}

}
