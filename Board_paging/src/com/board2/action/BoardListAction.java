package com.board2.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board2.model.BoardDAO;
import com.board2.model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		int rowsize = 3;
		int block = 3;
		int totalRecord = 0;
		int allPage = 0;
		
		int page = 0;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}else {
			page = 1;
		}
		
		int startNo = (page*rowsize)-(rowsize-1);
		int endNo = (page*rowsize);
		
		int startBlock = (((page-1)/block)*block)+1;
		int endBlock = (((page-1)/block)*block)+block;
		
		BoardDAO dao = BoardDAO.getInstance();
		
		totalRecord = dao.getBoardCount(); 
		System.out.println("totalRecord >>> "+totalRecord);
		
		allPage = (int)Math.ceil(totalRecord/(double)rowsize);
		
		if(endBlock>allPage) {
			endBlock = allPage;
		}
		
		List<BoardDTO> boardlist = dao.getBoardList(page,rowsize);
		
		
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("List", boardlist);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/board_list.jsp");
		
		return forward;
	}

}
