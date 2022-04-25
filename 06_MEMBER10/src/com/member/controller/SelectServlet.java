package com.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

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
		
		// 비지니스 로직
		// MEMBER10 테이블의 전체 회원 목록을 화면에 보여달라고 요청.
		
		// 1단계 : DB연동 작업
		MemberDAO dao = new MemberDAO();
		
		System.out.println("select dao >>>"+dao);
		
		// 2단계 : MEMBER10 테이블의 회원 전체 리스트를 조회해야 함.
		List<MemberDTO> memberlist = dao.getMemberList();
		
		// 3단계 : DB에서 가져온 정보를 view page로 이동시켜야 한다.
		
		request.setAttribute("member",memberlist );
		
		// 4단계 : 페이지 이동을 진행하자.
		RequestDispatcher rd = request.getRequestDispatcher("view/member_list.jsp");
		rd.forward(request, response);
		
		
		
		
		
	
	}

}
