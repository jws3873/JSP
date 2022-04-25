package com.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.shop.controller.Action;
import com.shop.controller.ActionForward;
import com.shop.model.ProductDAO;
import com.shop.model.ProductDTO;

public class AdminProductInputOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String saveFolder = "C:\\NCS\\workspace(jsp)\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\ShoppingMall\\upload";
		
		int fileSize = 10*1024*1024;
		
		MultipartRequest multi = new MultipartRequest(
				request,
				saveFolder,
				fileSize,
				"UTF-8",
				new DefaultFileRenamePolicy());
		
		String p_category = multi.getParameter("P_category").trim();
		String p_name = multi.getParameter("p_name").trim();
		String p_company = multi.getParameter("p_company").trim();
		int p_qty = Integer.parseInt(multi.getParameter("p_qty").trim());
		int p_price = Integer.parseInt(multi.getParameter("p_price").trim());
		String p_spec = multi.getParameter("p_spec").trim();
		String p_content = multi.getParameter("p_content").trim();
		int p_point = Integer.parseInt(multi.getParameter("p_point").trim());
		
		String p_image = multi.getFilesystemName("p_image");
		
		ProductDTO dto = new ProductDTO();
		dto.setPcategory_fk(p_category);
		dto.setPcompany(p_company);
		dto.setPcontents(p_content);
		dto.setPimage(p_image);
		dto.setPname(p_name);
		dto.setPoint(p_point);
		dto.setPrice(p_price);
		dto.setPspec(p_spec);
		dto.setPqty(p_qty);
		
		
		ProductDAO dao = ProductDAO.getInstance();
		
		int check = dao.insertProduct(dto);
		
		ActionForward forward = new ActionForward();
		
		PrintWriter out = response.getWriter();
		
		if(check>0) {
			forward.setRedirect(true);
			forward.setPath("admin_product_list.do");
		}else {
			out.println("<script>");
			out.println("alert('상품등록 실패')");
			out.println("history.back()");
			out.println("</script>");
			
		}
		return forward;
		
	}

}
