package com.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 상품 등록 폼 페이지를 통해서 ,DB에 저장된 전체 상품 목록을 조회하여
		// 상품 목록 페이지(view page)로 이동시키는 비지니스 로직
		ProductDAO dao = ProductDAO.getInstance();
		
		List<ProductDTO> list =  dao.getProductList();
		
		request.setAttribute("productList", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("admin/admin_product_list.jsp");
		
		return forward;
	}

}
