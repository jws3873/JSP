package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;
import com.shop.model.CategoryDTO;

public class AdminCartInputOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String cart_code = request.getParameter("cart_code");

		String cart_name = request.getParameter("cart_name");
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		int check = dao.insertCategory(cart_code,cart_name);
		
		System.out.println(check);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check>0) {
			forward.setRedirect(true);
			forward.setPath("admin_cart_list.do");
		}else {
			out.println("<script>");
			out.println("alert('카테고리 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
