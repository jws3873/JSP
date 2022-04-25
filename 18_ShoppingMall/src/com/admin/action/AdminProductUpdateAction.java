package com.admin.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// get방식으로 넘어온 제품번호에 해당하는 제품의 산세내역을
		// DB에서 조회하여 수정 폼 패아자(view page)로 이동시키는 비지니로직
		
		int pnum = Integer.parseInt(request.getParameter("pnum").trim());
		
		ProductDAO dao = ProductDAO.getInstance();
		
		ProductDTO dto = dao.getproductContent(pnum);
		
		request.setAttribute("productCont", dto);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("admin/admin_product_update.jsp");
		
		return forward;
	}

}
