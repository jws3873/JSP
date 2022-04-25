package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;

public class BoardDeleteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		
		String board_pwd = request.getParameter("pwd").trim();
		
		BoardDAO dao = BoardDAO.getInstance();
				
		
		int check = dao.deleteBoard(board_no,board_pwd);
		
		PrintWriter out = response.getWriter();
		
		if(check>0) {
			out.println("<script>");
			out.println("alert('게시물 삭제 성공!!')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		}else if(check==-1){
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
			
		}else {
			out.println("<script>");
			out.println("alert('게시물 삭제 실패~~')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		

	}

}
