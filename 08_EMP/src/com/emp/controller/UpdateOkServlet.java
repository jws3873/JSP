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


@WebServlet("/updateOk.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 수정 폼 페이지에서 넘어온 데이터들을 DB에 수정하는 작업.
		// 즉, 사원번호에 해당하는 사원 정보를 수정하는 작업.
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int re_empno = 
			Integer.parseInt(request.getParameter("num").trim());
		
		String re_name = request.getParameter("name").trim();
		
		String re_job = request.getParameter("job").trim();
		
		int re_mgrNo = 
			Integer.parseInt(request.getParameter("mgr").trim());
		
		int re_sal = 
			Integer.parseInt(request.getParameter("sal").trim());
		
		int re_comm = 
			Integer.parseInt(request.getParameter("comm").trim());
		
		int re_deptno = 
			Integer.parseInt(request.getParameter("deptno").trim());
		
		
		EmpDTO dto = new EmpDTO();
		
		dto.setEmpno(re_empno);
		
		dto.setEname(re_name);
		
		dto.setJob(re_job);
		
		dto.setMgr(re_mgrNo);
		
		dto.setSal(re_sal);
		
		dto.setComm(re_comm);
		
		dto.setDeptno(re_deptno);
		
		
		EmpDAO dao = EmpDAO.getInstance();
		
		int res = dao.updateEmp(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('사원 정보 수정 성공!!!')");
			out.println("location.href='content.do?no="+dto.getEmpno()+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('사원 정보 수정 실패~~~~')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
