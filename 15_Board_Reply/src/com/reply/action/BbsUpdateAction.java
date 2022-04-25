package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글 번호에 해당하는 상세내역을
		// 수정 폼 페이지로 전달하는 비지니스 로직.
		
		int bbs_no = Integer.parseInt(request.getParameter("no").trim());
		
		BbsDAO dao = BbsDAO.getInstance();
		
		BbsDTO dto = dao.getBbsContent(bbs_no);
		
		request.setAttribute("modify", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/bbs_update.jsp");
		
		return forward;
	}

}
