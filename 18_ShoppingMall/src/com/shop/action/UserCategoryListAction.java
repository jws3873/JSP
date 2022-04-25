package com.shop.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.CategoryDAO;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class UserCategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 카테고리 코드에 해당하는 제품 리스트를
		// 조회하여 view page로 이동시키는 비지니스 로직.
		
		String product_code = request.getParameter("code");
		
		ProductDAO dao = ProductDAO.getInstance();
		
		List<ProductDTO> list = dao.getProductList(product_code);
		
		request.setAttribute("productList", list);
		//*********user MainAction과 동일한 key를 부여하여 카테고리 코드를 클릭하면 동일한 키값이 전달되지만 리스트가 코드에 맞춰 들어간다*********		
		//*********user MainAction과 동일한 key를 부여하여 카테고리 코드를 클릭하면 동일한 키값이 전달되지만 리스트가 코드에 맞춰 들어간다*********		
		//*********user MainAction과 동일한 key를 부여하여 카테고리 코드를 클릭하면 동일한 키값이 전달되지만 리스트가 코드에 맞춰 들어간다*********		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false);
		forward.setPath("user/user_main.jsp");

		return forward;
	}

}
