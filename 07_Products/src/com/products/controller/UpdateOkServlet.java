package com.products.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.ProductsDAO;
import com.products.model.ProductsDTO;

/**
 * Servlet implementation class UpdateOkServlet
 */
@WebServlet("/update_ok.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateOkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int product_num = Integer.parseInt(request.getParameter("product_num").trim());
		
		String product_category = request.getParameter("product_category").trim();
		
		String product_name = request.getParameter("product_name").trim();
		
		String product_code = request.getParameter("product_code").trim();
		
		int product_input = Integer.parseInt(request.getParameter("product_input").trim());

		int product_output = Integer.parseInt(request.getParameter("product_output").trim());
		
		int product_transcost = Integer.parseInt(request.getParameter("product_transcost").trim());

		int product_mileage = Integer.parseInt(request.getParameter("product_mileage").trim());
		
		String product_company = request.getParameter("product_company").trim();
	
		ProductsDTO dto = new ProductsDTO();
		dto.setPnum(product_num);
		dto.setCategory_fk(product_category);
		dto.setProduct_name(product_name);
		dto.setEp_code_fk(product_code);
		dto.setInput_price(product_input);
		dto.setOutput_price(product_output);
		dto.setTrans_cost(product_transcost);
		dto.setMileage(product_mileage);
		dto.setCompany(product_company);
		
		ProductsDAO dao = ProductsDAO.getInstance();
		
		int check = dao.productUpdate(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check>0) {
			out.println("<script>");
			out.println("alert('제품 수정 성공!!')");
			out.println("location.href = 'content.do?no="+dto.getPnum()+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('제품 수정 실패~~)");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
