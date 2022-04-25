package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;


@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사원 삭제 버튼 클릭 시 get 방식으로 넘어온 사원 번호(no)를 가지고
		// DB에 가서 사원 번호에 해당하는 사원 정보를 DB에서 삭제하는 작업.
		response.setContentType("text/html; charset=UTF-8");
		
		int empno = 
			Integer.parseInt(request.getParameter("no"));
		
		EmpDAO dao = EmpDAO.getInstance();
		
		int res = dao.deleteEmp(empno);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {  // 사원 삭제가 성공한 경우
			out.println("<script>");
			out.println("alert('사원 삭제 성공!!!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('사원 삭제 실패~~~')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
