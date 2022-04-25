package com.board1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.action.*;

public class FrontController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//한글 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// "/프로젝트명/ 파일명(*.do)" 라는 문자열을 반환해 주는 메서드.
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		// 현재 프로젝트명은 문자열로 반환해 주는 메서드
		String path = request.getContextPath();
		System.out.println(path);
		
		String command = uri.substring(path.length()+1);
		System.out.println("command >>> "+command);
		
		
		Action action = null;
		String viewPage = null;
		
		if(command.equals("board_list.do")) {
			action = new BoardListAction();
			action.execute(request, response);
			viewPage = "view/board_list.jsp";
		}else if(command.equals("board_write.do")){
			viewPage = "view/board_write.jsp";
		}else if(command.equals("board_write_ok.do")) {
			action = new BoardWriteOkAction();
			action.execute(request, response);
		}else if(command.equals("board_content.do")) {
			action = new BoardContentAction();
			action.execute(request, response);
			viewPage = "view/board_content.jsp";
		}else if(command.equals("board_update.do")) {
			action = new BoardUpdateAction();
			action.execute(request, response);
			viewPage = "view/board_update.jsp";
		}else if(command.equals("board_update_ok.do")) {
			action = new BoardUpdateOkAction();
			action.execute(request, response);
		}else if(command.equals("board_delete.do")) {
			viewPage = "view/board_delete.jsp";
		}else if(command.equals("board_delete_ok.do")) {
			action = new BoardDeleteOkAction();
			action.execute(request, response);
		}else if(command.equals("board_search.do")) {
			action = new BoardSearchAction();
			action.execute(request, response);
			viewPage = "view/board_searchlist.jsp";
		}
		
		if(viewPage !=null) {
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		
		rd.forward(request, response);
		}
	}
}
