package com.board1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardWriteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//글쓰기 폼 페이지에서 넘어온 데이터들을 받아서
		// DB에 저장하는 비즈니스 로직
		
		String board_writer = request.getParameter("writer").trim();
		
		String board_title = request.getParameter("title").trim();
		
		String board_cont = request.getParameter("content").trim();
		
		String board_pwd = request.getParameter("pwd").trim();
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
		int check = dao.insertBoard(dto);
		
		PrintWriter out = response.getWriter();
		
		
		if(check>0) {
			out.println("<script>");
			out.println("alert('게시물 등록 성공!!')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시물 등록 실패~~')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
