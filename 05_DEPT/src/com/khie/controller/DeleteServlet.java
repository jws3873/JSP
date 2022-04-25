package com.khie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		// 1. 삭제 버튼을 눌렀을 때 get방식으로 넘어오는 부서번호를 받아야 한다.
		int deptno = Integer.parseInt(request.getParameter("deptno").trim());
		
		
		// 2. 삭제할 부서번호를 DB에 넘여주어야 한다.
		DeptDAO dao =  new DeptDAO();
		int res = dao.deleteDept(deptno);
		
		PrintWriter out = response.getWriter();
		
		if(res>0) {
			out.println("<script>");
			out.println("alert('부서 삭제 성공!!!')");
			out.println("location.href='select'"); //select 서블릿 호출
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('부서 삭제 실패!!!')");
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>");
			
		}
	
	}

}
