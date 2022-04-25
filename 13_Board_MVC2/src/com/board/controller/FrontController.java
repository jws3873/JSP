package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.*;

public class FrontController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String path = request.getContextPath();
		System.out.println(path);
		
		String command = uri.substring(path.length()+1);
		System.out.println(command);
		
		
		Action action = null;
		String viewPage = null;
		
		if(command.equals("board_list.do")) {
			action = new BoardListAction();
			action.execute(request, response);
			viewPage = "view/board_list.jsp";
			
		}else if(command.equals("board_write.do")) {
			viewPage = "view/board_write.jsp";
			
		}else if(command.equals("board_write_ok.do")) {
			action = new BoardWriteOkAction();
			action.execute(request, response);
			
		}else if(command.equals("board_cont.do")) {
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
			viewPage ="view/board_delete.jsp";
		}else if(command.equals("board_delete_ok.do")) {
			action = new BoardDeleteOkAction();
			action.execute(request, response);
		}else if(command.equals("board_search.do")) {
			action = new BoardSearchAction();
			action.execute(request, response);
			viewPage = "view/board_searchList.jsp";
		}
		
		if(viewPage !=null) {
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		
		rd.forward(request, response);
		}
		
		
		
		
	}
}
