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
		// get방식으로 넘어온 카테고리 번호에 해당하는 카테고리를
		// DB에서 삭제하는 비지니스 로직
		int cart_num = Integer.parseInt(request.getParameter("cnum").trim());
		
		CategoryDAO dao = CategoryDAO.getInstance();
		
		int check = dao.deleteCartegory(cart_num);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(true);
		forward.setPath("admin_cart_list.do");
		return forward;
	}

}
