package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;


@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB에 접속하여 EMP 테이블의 전체 리스트를 가져오는 작업(비지니스 로직).
		// 가져온 전체 리스트를 View Page로 넘겨주는 작업.
		
		EmpDAO dao = EmpDAO.getInstance();
		
		// DB의 EMP 테이블에서 전체 사원 리스트를 조회하는 작업.
		List<EmpDTO> list = dao.allList();
		
		// 페이지 이동 시 데이터도 같이 이동시키자.
		request.setAttribute("List", list);
		
		// 페이지 이동
		RequestDispatcher rd = 
				request.getRequestDispatcher("view/emp_list.jsp");
		
		rd.forward(request, response);
		
	}

}
