package com.board2.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board2.action.*;

public class FrontController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
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

		// command문자열 변수에는 *.do 만 반환이 됨.
		String command = uri.substring(path.length() + 1);
		System.out.println("Command>>>"+command);
		
		Action action = null;
		
		ActionForward forward = null;
		
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream("C:\\NCS\\workspace(jsp)\\Board_paging\\src\\com\\board2\\controller\\mapping.propertise");
		
		prop.load(fis);
		
		
		String value = prop.getProperty(command);
		System.out.println("value >>> " + value);
		
		if(value.substring(0, 7).equals("execute")) {
			StringTokenizer st = new StringTokenizer(value,"|");
			String url_1 = st.nextToken();
			String url_2 = st.nextToken();
			
			
			try {
				Class url = Class.forName(url_2);
				
				action = (Action)url.newInstance();
				
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath(value);
			
		}
		
		if(forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
				rd.forward(request, response);
			}
		}
	
	
	
	
	}
}
