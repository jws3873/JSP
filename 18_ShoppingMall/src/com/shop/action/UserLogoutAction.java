package com.shop.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;

public class UserLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 로그 아웃 글자를 클릭하면 현재 모든 정보를 
		// 종료시켜 주는 비지니스 로직
		
		HttpSession session = request.getSession();
		session.invalidate(); // 모든 세션 정보를 만료시키는 메서드.
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("main.jsp");
		
		return forward;

	}

}
