package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//get 방식으로 넘어온 회원 번호를 회원 삭제 폼 페이지로 
		// 이동시키는 비지니스 로직
		
		int mem_num = Integer.parseInt(request.getParameter("num").trim());
		
		request.setAttribute("No", mem_num);
		
		return "view/member_delete.jsp";
	}

}
