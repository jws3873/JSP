package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 삭제 폼 페이지에서 넘어온 글 번호와 비밀번호를 가지고 
		// DB에서 게시글을 삭제하는 비지니스로직

		int upload_no = Integer.parseInt(request.getParameter("upload_no").trim());
		
		String upload_pwd = request.getParameter("upload_pwd").trim();
		
		
		UploadDAO dao = UploadDAO.getInstance();
		
		UploadDTO dto =  dao.uploadContent(upload_no);
		
		// upload 폴더에 업로드된 파일까지 삭제
		
		String upload = "C:\\NCS\\workspace(jsp)\\16_Board_FileUpload\\WebContent\\upload";
		
		// DB에서 업로드된 파일을 가져오자.
		String fileName = dto.getUpload_file();
		
		int check =0;
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(upload_pwd.equals(dto.getUpload_pwd())) {
			check = dao.deleteUpload(upload_no,upload_pwd);
			if(fileName != null) { // 첨부파일이 존재하는 경우
				File file = new File(upload+fileName);
				file.delete(); // 기존의 이진파일 제거하는 메서드.
			}
			if(check>0) {
				forward.setRedirect(true);
				forward.setPath("upload_list.do");
			}else {
				out.println("<script>");
				out.println("alert('자료실 게시판 게시물 삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
			}
			
		}else{
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다 확인해 주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		
		return forward;
	}

}
