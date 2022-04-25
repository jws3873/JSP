package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class ContentServlet
 */
@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get 방식으로 넘어온 글 번호를 가지고 DB에서 조회하여 
		// 글 번호에 해당하는 게시글 상세 정보를 veiw page로 이동시키는 비지니스 로직
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 조회수를 증사기켜 주는 메서드 호출
		dao.boardHit(board_no);
		
		// 상세 내역을 조회하는 메서드 호출
		BoardDTO cont = dao.BoardContent(board_no);

		request.setAttribute("content", cont);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/board_content.jsp");
		
		rd.forward(request, response);
	
	
	}

}
