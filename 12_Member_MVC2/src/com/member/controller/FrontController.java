package com.member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.action.*; //action pakage에 많은것들이 생길거다.

// Servlet - FrontController
public class FrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//getRequestURI() :"/프로젝트명/파일명(*.do)"라는 문자열을 반환해 주는 메서드.
		String uri = request.getRequestURI();
		System.out.println("URI >>> "+uri);
		
		// getContextPath() : 현재 프로젝트명을 문자열로 반환해 주는 메서드.
		String path = request.getContextPath();
		System.out.println("Path"+path);
		
		String command = uri.substring(path.length() + 1);
		System.out.println("Command>>>"+command);
		/*
		 * substring() 메소드는 다음과 같이 2가지 형태로 사용할 수 있습니다.

			public String substring(int startIndex)
			
			public String substring(int startIndex, int endIndex)
			
			
			 substring(int startIndex) 
			startIndex부터 끝까지의 문자열을 리턴합니다.
			
			 substring(int startIndex, int endIndex) 
			startIndex(포함)부터 endIndex(불포함)까지의 문자열을 리턴합니다.

		 */
		
		Action action = null;
		
		if(command.equals("select.do")) {
			action = new MemberListAction();
		}else if(command.equals("insert.do")){
			action = new MemberJoinAction();
		}else if(command.equals("insert_ok.do")) {
			action = new MemberJoinOkAction();
		}else if(command.equals("content.do")) {
			action = new MemberContentAction();
		}else if(command.equals("update.do")) {
			action = new MemberUpdateAction();
		}else if(command.equals("update_ok.do")) {
			action = new MemberUpdateOkAction();
		}else if(command.equals("delete.do")) {
			action = new MemberDeleteAction();
		}else if(command.equals("delete_ok.do")) {
			action = new MemberDeleteOkAction();
		}
		
		String path1 = action.execute(request, response); 
		
		RequestDispatcher rd = request.getRequestDispatcher(path1);
		
		rd.forward(request, response);
	
	}
}
