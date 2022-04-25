package com.emp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpDAO;
import com.emp.model.EmpDTO;


@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 넘어온 사번에 해당하는 사원의 상세정보를 조회하는 작업.
		
		int empno = 
			Integer.parseInt(request.getParameter("no"));
		
		EmpDAO dao = EmpDAO.getInstance();
		
		EmpDTO cont = dao.getContent(empno);
		
		request.setAttribute("content", cont);
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("view/emp_content.jsp");
		
		rd.forward(request, response);
		
	}

}
