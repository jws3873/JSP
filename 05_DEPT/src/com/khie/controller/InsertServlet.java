package com.khie.controller;
import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertServelt
 */
@WebServlet("/insert_ok")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// insert.jsp 페이지에서 넘어온 데이터들을 DEPT 테이블에 저장하는 로직.
		
		// 한글 인코딩 처리 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계: 부서등록 폼 페이지에서 넘어온 데이터들을 받아주어야 한다.
		int deptno =Integer.parseInt(request.getParameter("deptno").trim());
		
		String deptName = request.getParameter("deptName").trim();
		
		String location = request.getParameter("location").trim();
		
		// 2단계 : DTO 객체를 이용하여 DB에 전송을 해야 한다.
		DeptDTO dto = new DeptDTO();
		
		dto.setDeptno(deptno);
		dto.setDname(deptName);
		dto.setLoc(location);
		
		// 3단계 : DB에 DTO 객체를 전송
		DeptDAO dao = new DeptDAO();
		int res = dao.insertDept(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res>0) {
			out.println("<script>");
			out.println("alert('부서 추가 성공!!!')");
			out.println("location.href='select'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('부서 추가 실패!!!')");
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>");
			
		}
	}

}
