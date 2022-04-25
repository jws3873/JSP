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
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select.do")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DB와 연동하여 customer 테이블의 전체 리스트를
		// 조회하는 비지니스 로직.
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out =  response.getWriter();
		
		CustomerDAO dao = CustomerDAO.getInstance();
		
		String str = dao.getCustomerList();
		
		// 반환된 고객정보를 클라이언트(ajax) 쪽으로 응답을 해주면 된다.
		
		out.println(str);
		
	}

}
