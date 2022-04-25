package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;

public class AdminCartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
int cart_num = Integer.parseInt(request.getParameter("cnum").trim());
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		int check = dao.deleteCategory(cart_num);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(true);
		forward.setPath("admin_cart_list.do");
		return forward;
	}

}
