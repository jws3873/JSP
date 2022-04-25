package com.board1.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		//get 방식으로 넘어온 글 번호를 가지고 DB에서 상세내역을 가져온 후 
		// 수정 폼 페이지로 해당 상세 내역을 넘겨 주는 비지니스 로직.
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		
		BoardDAO dao = BoardDAO.getInstance();
		
		
		// 글번호에 해당하는 게시글의 상세 내역을 조회하는 메서드 호출
		BoardDTO dto =  dao.getBoardCont(board_no);
		
		
		request.setAttribute("Modify", dto);
		request.setAttribute("page", nowPage);
	}

}
