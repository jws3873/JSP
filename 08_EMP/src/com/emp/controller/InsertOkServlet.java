package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;


@WebServlet("/insertOk.do")
public class InsertOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public InsertOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사원 등록 폼 페이지에서 넘어온 데이터들을 DB에 저장하는 작업
		
		// 한글 깨짐 방지 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 사원 등록 폼 페이지에서 넘어온 데이터들을 받아 주어야 한다.
		int empno = 
			Integer.parseInt(request.getParameter("num").trim());
		
		String ename = request.getParameter("name").trim();
		
		String job = request.getParameter("job").trim();
		
		int mgr = 
			Integer.parseInt(request.getParameter("mgr").trim());
		
		int sal = 
			Integer.parseInt(request.getParameter("sal").trim());
		
		int comm = 
			Integer.parseInt(request.getParameter("comm").trim());
		
		int deptno = 
			Integer.parseInt(request.getParameter("dept").trim());
		
		
		EmpDTO dto = new EmpDTO();
		
		dto.setEmpno(empno);
		
		dto.setEname(ename);
		
		dto.setJob(job);
		
		dto.setMgr(mgr);
		
		dto.setSal(sal);
		
		dto.setComm(comm);
		
		dto.setDeptno(deptno);
		
		
		EmpDAO dao = EmpDAO.getInstance();
		
		int res = dao.insertEmp(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {  // 사원 추가가 성공한 경우
			out.println("<script>");
			out.println("alert('사원 등록 성공!!!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {  // 사원 추가가 실패한 경우
			out.println("<script>");
			out.println("alert('사원 등록 실패~~~')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
