package com.products.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.ProductsDAO;
import com.products.model.ProductsDTO;

/**
 * Servlet implementation class ContentServlet
 */
@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		int pnum = Integer.parseInt(request.getParameter("no").trim());
		
		ProductsDAO dao = ProductsDAO.getInstance();
		
		
		ProductsDTO productcontent = dao.productContent(pnum);
		
		request.setAttribute("content", productcontent);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/product_content.jsp");
		
		rd.forward(request, response);
	}

}
