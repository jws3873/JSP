package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.DeptDTO;
import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;


@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 받은 사원 번호에 대한 정보를 수정 폼 창으로 전달하는 작업.
		
		int empno = 
			Integer.parseInt(request.getParameter("no"));
		
		EmpDAO dao = EmpDAO.getInstance();
		
		// 담당업무에 대한 정보를 조회하는 메서드 호출
		List<String> jobList = dao.getJobList();
		
		// 관리자에 대한 정보를 조회하는 메서드 호출
		List<EmpDTO> mgrList = dao.getMgrList();
		
		// 부서번호에 해당하는 정보를 조회하는 메서드 호출
		List<DeptDTO> deptList = dao.getDeptList();
		
		// 사원번호에 해당하는 정보를 조회하는 메서드 호출
		EmpDTO dto = dao.getContent(empno);
		
		request.setAttribute("jList", jobList);
		
		request.setAttribute("mList", mgrList);
		
		request.setAttribute("dList", deptList);
		
		request.setAttribute("modify", dto);
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("view/emp_modify.jsp");
		
		rd.forward(request, response);
		
		
	}

}
