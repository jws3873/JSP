package com.board1.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 글 제목 클릭시 get 방식으로 넘어온 글 번호에 해당하는 
		// 게시글의 상세 내역을 조회하여  view page로 이동시키는 비즈니스 로직
		
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int nowPage = Integer.parseInt(request.getParameter("page").trim());
		
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// 글 제목 클릭 시 조회 수 증가시키는 메서드 호출
		dao.boardHit(board_no);
		
		// 글번호에 해당하는 게시글의 상세 내역을 조회하는 메서드 호출
		BoardDTO dto =  dao.getBoardCont(board_no);
		
		
		request.setAttribute("Cont", dto);
		
		request.setAttribute("page", nowPage);
		
	}

}
