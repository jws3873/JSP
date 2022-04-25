package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// member_join.jsp 페이지에서 넘어온 회원등록 관련 정보들을 DB상의
		// MEMBER10 테이블에 저장하는 비즈니스 로직
		
		// 폼 페이지에서 한글이 입력이 되면 한글 깨짐 방지 되도록 설정
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		// 1단계 : 회원등록 폼 페이지에서 넘어온 데이터들을 받아 주어야 한다.
		String member_id = request.getParameter("mem_id").trim();
		
		String member_name = request.getParameter("mem_name").trim();
		
		String member_pwd = request.getParameter("mem_pwd").trim();
		
		int member_age = Integer.parseInt(request.getParameter("mem_age").trim());
		
		int member_mileage = Integer.parseInt(request.getParameter("mem_mileage").trim());
		
		String member_job = request.getParameter("mem_job").trim();
		
		String member_addr = request.getParameter("mem_addr").trim();
		
		MemberDTO dto = new MemberDTO();
		
		dto.setMemid(member_id);
		
		dto.setMemname(member_name);
		
		dto.setPwd(member_pwd);
		
		dto.setAge(member_age);
		
		dto.setMileage(member_mileage);
		
		dto.setJob(member_job);
		
		dto.setAddr(member_addr);
		
		MemberDAO dao = new MemberDAO();
		
		int res = dao.insertMember(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res>0) {
			out.println("<script>");
			out.println("alert('회원등록 성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}

	}

}
