package com.board2.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board2.model.BoardDAO;
import com.board2.model.BoardDTO;

public class BoardWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String board_writer = request.getParameter("writer").trim();
		
		String board_title = request.getParameter("title").trim();
		
		String board_content = request.getParameter("content").trim();
		
		String board_pwd = request.getParameter("pwd").trim();
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);

		BoardDAO dao = BoardDAO.getInstance();
		
		int check = dao.insertBoard(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check>0) {
			out.println("<script>");
			out.println("alert('게시글 등록 성공')");
			out.println("</script>");
			forward.setRedirect(true);
			forward.setPath("board_list.do");
		}else {
			out.println("<script>");
			out.println("alert('게시글 등록 실패')");
			out.println("</script>");
			forward.setRedirect(false);
			forward.setPath("view/board_write.jsp");
		}
		
		return forward;
	}

}
