package com.board2.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board2.model.BoardDAO;

public class DeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int board_no = Integer.parseInt(request.getParameter("no").trim());

		int page = Integer.parseInt(request.getParameter("page").trim());
		
		String board_pwd = request.getParameter("pwd").trim();
		
		
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.deleteBoard(board_no,board_pwd);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check>0) {
			out.println("<script>");
			out.println("alert('삭제 성공')");
			out.println("</script>");
			forward.setRedirect(false);
			forward.setPath("board_list.do?page="+page);
		}else if(check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호를 확인해주세요')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
