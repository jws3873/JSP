package com.board1.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// DB에서 게시글 전체 리스트 조회하여 처리하는 비지니스 로직.
		// 비지니스 로직 수행 시 페이징 처리 작업 동시에 진행.
		
		// BoardDAO dao = BoardDAO.getInstance();
		
		// List<BoardDTO> boardlist= dao.getBoardList();
		
		// request.setAttribute("List", boardlist);
		
		// 페이징 처리 작업 진행 Spring에서 DTO방식으로 진행예정
		int rowsize = 3;		// 한 페이지당 보여질 게시물 수 
		int block = 3; 			// 아래에 보여질 페이지의 최대 수 예 [1][2][3]/[4][5][6]
		int totalRecord = 0; 	// DB 상의 게시물의 전체 수
		int allPage = 0;		// 전체 페이지 수
		
		int page = 0;			// 현재 페이지 변수
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page").trim());
		}else { // 처음으로 "전체 게시물 목록" a태그를 선택한 경우
			page = 1;
		}
		
		// 해당 페이지에서 시작 번호
		int startNo = (page*rowsize) - (rowsize-1);
		
		// 해당 페이지에서 끝 번호 
		int endNo = (page*rowsize);
		
		// 해당 페이지에서 시작 블럭
		int startBlock = (((page-1)/block)*block)+1;
		
		// 해당 페이지에서 끝 블럭
		int endBlock = (((page-1) / block ) * block) + block;
		
		BoardDAO dao = BoardDAO.getInstance();
		
		// DB상의 전체 게시물의 수를 확인하는 메서드 호출
		totalRecord = dao.getBoardCount();
		
		
		// 전체 게시물의 수를 한페이지당 보여질 게시물의 수로 나누어 주어야 함.
		// 이 과정을 거치면 전체 페이지 수가 나오게 됨.
		// 전체 페이지 수가 나올 때 나머지가 있으면 무조건 페이지수를 하나 올려주어야 함.
		allPage = (int)Math.ceil( totalRecord / (double)rowsize);
		
		if(endBlock>allPage) {
			endBlock = allPage;
		}
		
		// 현재 페이지에서 해당하는 게시물을 가져오는 메서드.
		List<BoardDTO> pageList =  dao.getBoardList(page,rowsize);
		
		// 지금까지 페이징 처리 시 작업했던 모든 값들을 view 페이지로 이동시키자.
		request.setAttribute("page", page);
		request.setAttribute("rowsize", rowsize);
		request.setAttribute("block", block);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("allPage", allPage);
		request.setAttribute("startNo", startNo);
		request.setAttribute("endNo", endNo);
		request.setAttribute("startBlock", startBlock);
		request.setAttribute("endBlock", endBlock);
		request.setAttribute("List", pageList);
		
	}

}
