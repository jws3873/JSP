package com.upload.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// get 방식으로 넘어온 글번호에 해당하는 게시글의 상세내역을 조회하여 
		// 해당 게시글을 view page로 이동시키는 비즈니스 로직

		int upload_no = Integer.parseInt(request.getParameter("no").trim());

		UploadDAO dao = UploadDAO.getInstance();
		
		UploadDTO dto = dao.uploadContent(upload_no);
		
		request.setAttribute("Modify", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("view/upload_update.jsp");
		
		return forward;
	}

}
