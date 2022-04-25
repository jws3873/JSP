package com.board1.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardUpdateOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
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
		
		PrintWriter out = response.getWriter();
		
		if(db_pwd.equals(board_pwd)) {
			int check = dao.boardupdate(dto);
			if(check>0) {
				out.println("<script>");
				out.println("alert('게시물 수정 성공!!')");
				out.println("location.href='board_content.do?no="+board_no+"&page="+nowPage+"'");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('게시물 수정 실패~~')");
				out.println("history.back()");
				out.println("</script>");
			}
		}else {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인해 주세요')");
			out.println("history.go(-1)");
			out.println("</script>");
		}
		
	}

}
