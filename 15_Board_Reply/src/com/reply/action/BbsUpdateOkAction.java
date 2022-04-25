package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 수정 폼 페이지에 넘어온 데이터들을 가지고 
		// DB에서 수정하는 비지니스 로직.
		
		String bbs_writer = request.getParameter("writer").trim();
		
		String bbs_title = request.getParameter("title").trim();
		
		String bbs_cont = request.getParameter("content").trim();
		
		String bbs_pwd = request.getParameter("pwd").trim();
		
		// 히든으로 넘어온 데이터도 받아주어야 한다.
		int bbs_no = Integer.parseInt(request.getParameter("bbs_no").trim());
		
		
		BbsDTO dto = new BbsDTO();
		
		dto.setBoard_no(bbs_no);
		
		dto.setBoard_writer(bbs_writer);
		
		dto.setBoard_title(bbs_title);
		
		dto.setBoard_cont(bbs_cont);
		
		dto.setBoard_pwd(bbs_pwd);
		
		
		BbsDAO dao = BbsDAO.getInstance();
		
		int check = dao.updateBbs(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check>0) {
			forward.setRedirect(true);
			forward.setPath("bbs_content.do?no="+bbs_no);
		}else if(check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인해 주세요~~')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시물 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
