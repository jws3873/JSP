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


@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사원등록 폼으로 넘어가기 전에 담당업무 리스트와 관리자 리스트,
		// 부서번호 리스트를 DB에서 조회하여 사원 등록 폼 페이지로 넘겨주자.
		
		EmpDAO dao = EmpDAO.getInstance();
		
		// EMP 테이블에서 담당업무 리스트를 조회하자.
		List<String> jobList = dao.getJobList();
		
		// EMP 테이블에서 담당업무가 "MANAGER"인 사원 리스트 조회하자.
		List<EmpDTO> mgrList = dao.getMgrList();
		
		// 부서 테이블 전체 리스트를 조회하자.
		List<DeptDTO> deptList = dao.getDeptList();
		
		// 페이지 이동 시 데이터도 같이 넘겨 주자.
		request.setAttribute("job", jobList);
		
		request.setAttribute("mgr", mgrList);
		
		request.setAttribute("dept", deptList);
		
		// 페이지 이동
		RequestDispatcher rd = 
				request.getRequestDispatcher("view/emp_insert.jsp");
		
		rd.forward(request, response);
		
	}

}
