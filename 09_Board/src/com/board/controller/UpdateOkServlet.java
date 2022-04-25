package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class UpdateOkServlet
 */
@WebServlet("/update_ok.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정 폼 페이지에서 넘어온 데이터들을 DB에 저장시키는 비지니스 로직.
		// 한글 처리 작업 진행
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String board_writer = request.getParameter("writer").trim();
		
		String board_title = request.getParameter("title").trim();
		
		String board_content = request.getParameter("content").trim();
		
		String board_pwd = request.getParameter("pwd").trim();
	
		int board_no = Integer.parseInt(request.getParameter("board_no").trim());
		
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_no(board_no);
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_content);
		dto.setBoard_pwd(board_pwd);
		
		BoardDAO dao = BoardDAO.getInstance();
				
		int check = dao.updateBoard(dto);
		
		PrintWriter out =
		response.getWriter();
		
		if(check>0) {
			out.println("<script>");
			out.println("alert('게시물 수정 성공')");
			out.println("location.href='content.do?no="+dto.getBoard_no()+"'");
			out.println("</script>");
		}else if(check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시물 수정 실패.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
