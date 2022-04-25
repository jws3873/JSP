package com.board.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String search_field = request.getParameter("search_field").trim();
		
		String search_keward = request.getParameter("search_keyword").trim();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		List<BoardDTO> searchlist = dao.getsearchList(search_field,search_keward);
		
		request.setAttribute("Search", searchlist);
		
	}

}
