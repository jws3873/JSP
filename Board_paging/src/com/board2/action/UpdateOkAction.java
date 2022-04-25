package com.board2.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board2.model.BoardDAO;
import com.board2.model.BoardDTO;

public class UpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int board_no = Integer.parseInt(request.getParameter("no").trim());

		int page = Integer.parseInt(request.getParameter("page").trim());
		
		String db_pwd = request.getParameter("db_pwd").trim();
		
		
		String board_writer = request.getParameter("writer").trim();
		
		String board_title = request.getParameter("title").trim();
		
		String board_cont = request.getParameter("content").trim();
		
		String board_pwd = request.getParameter("pwd").trim();
		
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_no(board_no);
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if (db_pwd.equals(dto.getBoard_pwd())) {
			int check = dao.insertUpdate(dto);
			if(check >0) {
				out.println("<script>");
				out.println("alert('수정 완료')");
				out.println("</script>");
				forward.setRedirect(true);
				forward.setPath("board_content.do?no="+board_no+"&page="+page);
			}else {
				out.println("<script>");
				out.println("alert('수정 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {
			out.println("<script>");
			out.println("alert('비밀번호를 확인해주세요')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
