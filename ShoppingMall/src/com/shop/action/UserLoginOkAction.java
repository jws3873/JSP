package com.shop.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.UserDAO;
import com.shop.model.UserDTO;

public class UserLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String userId = request.getParameter("user_id").trim();
		String userPwd = request.getParameter("user_pwd").trim();
		
		UserDAO dao = UserDAO.getInstance();
		
		int check = dao.userCheck(userId,userPwd);
		
		HttpSession session = request.getSession();
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		
		if(check>0) {
			UserDTO dto = dao.getMember(userId);
			session.setAttribute("userId", dto.getMemid());
			session.setAttribute("userName",dto.getMemname());
			forward.setRedirect(true);
			forward.setPath("user_main.do");
		}else if(check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			// 회원이 아닌 경우(아이디가 없거나 비밀번호가 틀린 경우)
			
			out.println("<script>");
			out.println("alert('없는 아이디입니다. 다시 한번 아이디를 확인하세요')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
