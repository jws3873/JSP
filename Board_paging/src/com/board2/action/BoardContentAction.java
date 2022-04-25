package com.board2.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board2.model.BoardDAO;
import com.board2.model.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int board_no = Integer.parseInt(request.getParameter("no").trim());

		int page = Integer.parseInt(request.getParameter("page").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.boardHit(board_no);
		
		BoardDTO boardcont = dao.getBoardCont(board_no);
		
		request.setAttribute("Cont", boardcont);
		
		request.setAttribute("page", page);
		
		ActionForward foward = new ActionForward();
		
		foward.setRedirect(false);
		foward.setPath("view/board_content.jsp");
		
		return foward;
	}

}
