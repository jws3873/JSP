package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadUpdateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		UploadDTO dto = new UploadDTO();
		
		String saveFolder = "C:\\NCS\\workspace(jsp)\\Board_FileUpload\\WebContent\\upload";
		
		int fileSize = 10 * 1024 * 1024;
		
		MultipartRequest multi = new MultipartRequest(
				request,
				saveFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		String upload_writer = multi.getParameter("upload_writer");
		String upload_title = multi.getParameter("upload_title");
		String upload_cont = multi.getParameter("upload_content");
		String upload_pwd = multi.getParameter("upload_pwd");
		
		File upload_file = multi.getFile("upload_file");
		
		int upload_no = Integer.parseInt(multi.getParameter("upload_no").trim());
		
		if(upload_file != null) {
			
			String fileName = upload_file.getName();
			
			Calendar cal = Calendar.getInstance();
			
			int year = cal.get(Calendar.YEAR);
			int month =cal.get(Calendar.MONTH)+1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			String homedir = saveFolder+"/"+year+"-"+month+"-"+day;
			
			File path1 = new File(homedir);
			
			if(!path1.exists()) {
				path1.mkdir();
			}
			
			String reFileName = upload_writer+"_"+fileName;
			
			upload_file.renameTo(new File(homedir+"/"+reFileName));
			
			String fileDBName = "/"+year+"-"+month+"-"+day+"/"+reFileName;
			
			dto.setUpload_file(fileDBName);
			
		}
		
		dto.setUpload_no(upload_no);
		dto.setUpload_writer(upload_writer);
		dto.setUpload_title(upload_title);
		dto.setUpload_cont(upload_cont);
		dto.setUpload_pwd(upload_pwd);
		
		UploadDAO dao = UploadDAO.getInstance();
		
		int check = dao.updateUpload(dto);
		
		PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		
		if(check >0) {
			forward.setRedirect(true);
			forward.setPath("upload_content.do?no="+upload_no);
		}else if(check == -1){
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다. 확인해 주세요.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('자료실 게시글 수정 실패~~~')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
