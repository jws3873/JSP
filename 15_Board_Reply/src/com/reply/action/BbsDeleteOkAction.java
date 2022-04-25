package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;

public class BbsDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 삭제 폼 페이지에 섬어온 데이터들을 가지고
		// DB에서 해당 글을 삭제하는 비지니스 로직.
		
		int bbs_no = Integer.parseInt(request.getParameter("no").trim());
		
		String bbs_pwd = request.getParameter("pwd").trim();
		
		
		BbsDAO dao = BbsDAO.getInstance();
		
		int check = dao.deleteBbs(bbs_no,bbs_pwd);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check >0) {
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		}else if(check == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인해 주세요~~')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('게시글 삭제 실패~~')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
