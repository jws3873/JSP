package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 게시글 작성 폼 페이지에서 넘어온 데이터들을
		// DB에 저장하는 비지니스 로직.
		
		String bbs_writer = request.getParameter("writer").trim();
		String bbs_title = request.getParameter("title").trim();
		String bbs_cont = request.getParameter("content").trim();
		String bbs_pwd = request.getParameter("pwd").trim();
		
		BbsDTO dto = new BbsDTO();
		
		dto.setBoard_writer(bbs_writer);
		dto.setBoard_title(bbs_title);
		dto.setBoard_cont(bbs_cont);
		dto.setBoard_pwd(bbs_pwd);
		
		BbsDAO dao = BbsDAO.getInstance();
		
		int check = dao.insertBoard(dto);
		
		PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		
		if(check>0) {
			out.println("<script>");
			out.println("alert('게시물 추가 성공!!!')");
			out.println("</script>");
			forward.setRedirect(true); //view가아닌 *.do로갈때
			forward.setPath("bbs_list.do");
		}else {
			out.println("<script>");
			out.println("alert('게시물 추가 실패!!!')");
			out.println("</script>");
			forward.setRedirect(false); // view로 이동시
			forward.setPath("view/bbs_write.jsp");
		}
		
		return forward;
		
	}
}
