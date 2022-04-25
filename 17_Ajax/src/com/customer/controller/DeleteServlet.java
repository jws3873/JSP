package com.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.CustomerDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete.do")
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
		// customer 테이블에서 번호에 해당하는 회원을
		// DB에서 삭제하는 비지니스 로직.
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out =  response.getWriter();	
		
		int user_no = Integer.parseInt(request.getParameter("no").trim());
		
		CustomerDAO dao = CustomerDAO.getInstance();
		
		int check = dao.deleteCustomer(user_no);
		
		out.println(check);
		
		

		
	}

}
