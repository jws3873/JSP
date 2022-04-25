package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsReplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		// 답변 글 폼 페이지에서 넘어온 데이터들을
		// DB에 저장하는 비지니스 로직.
		String reply_writer = request.getParameter("reply_writer").trim();
		String reply_title = request.getParameter("reply_title").trim();
		String reply_content = request.getParameter("reply_content").trim();
		String reply_pwd = request.getParameter("reply_pwd").trim();
		
		
		// 히든으로 넘어온 데이터들고 받아 주자
		int reply_no = Integer.parseInt(request.getParameter("reply_no").trim());
		int reply_group = Integer.parseInt(request.getParameter("reply_group").trim());
		int reply_step = Integer.parseInt(request.getParameter("reply_step").trim());
		int reply_indent = Integer.parseInt(request.getParameter("reply_indent").trim());
		
		BbsDTO dto = new BbsDTO();
		
		dto.setBoard_no(reply_no);
		dto.setBoard_writer(reply_writer);
		dto.setBoard_title(reply_title);
		dto.setBoard_cont(reply_content);
		dto.setBoard_pwd(reply_pwd);
		dto.setBoard_group(reply_group);
		dto.setBoard_step(reply_step);
		dto.setBoard_indent(reply_indent);
		
		BbsDAO dao = BbsDAO.getInstance();
		
		// 만약에 원글에 기존에 작성했던 답변글이 존재하는 경우
		// 해당 답변글에  step을 하나 증가시켜 주는 메서드
		dao.replyUpdate(reply_group,reply_step);
		
		// 답변글을 DB에 저장하는 메서드 호출
		int check = dao.replyBbs(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check >0) {
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		}else {
			out.println("<script>");
			out.println("alert('게시물 답변글 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
