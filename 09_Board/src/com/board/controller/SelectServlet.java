package com.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	// DB에서 board 테이블의  게시글 전체 목록을 조회하여 
	// 넘겨 받은 후에 view page로 이동시키는 비지니스 로직.
	BoardDAO dao = BoardDAO.getInstance();
	
	List<BoardDTO> boardlist = dao.getBoardList();
	
	request.setAttribute("bList", boardlist);
	
	RequestDispatcher rd = request.getRequestDispatcher("view/board_list.jsp");
	
	rd.forward(request, response);
	
	
	}

}
