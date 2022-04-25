package com.khie.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select")
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
		// 비지니스 로직
		// index.jsp 페이지에서 요청 ==> 전체 부서 목록 
		// 요청에 대해서 응답
		
		// 1 단계 : DB와 연결 작업 진행 
		// 준비과정 : DAO(Data Access Object) 객체만들어야 함.
		// 			DTO(Data Transfer Object) 객체를 만들어야 함.
		
		DeptDAO dao = new DeptDAO();
		
		//2 단계 : DB에서 DEPT테이블의 전체 목록을 조회
		List<DeptDTO> deptlist = dao.selectList();
		
		//3 단계 : 페이지 이동작업 진행 시 데이터를 같이 넘겨주어야 한 
		
		request.setAttribute("list", deptlist);
		
		// 4단계 : 실제로 페이지 이동을 진행해야 함.
		RequestDispatcher rd = request.getRequestDispatcher("select.jsp");
		
		rd.forward(request, response);
		
		 
		
		
	}

}
