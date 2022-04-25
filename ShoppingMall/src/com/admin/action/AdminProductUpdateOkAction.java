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

public class AdminProductUpdateOkAction implements Action {

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
		
		String p_category = multi.getParameter("p_category").trim();
		String p_name = multi.getParameter("p_name").trim();
		String p_company = multi.getParameter("p_company").trim();
		int p_qty = Integer.parseInt(multi.getParameter("p_qty").trim());
		int p_price = Integer.parseInt(multi.getParameter("p_price").trim());
		String p_spec = multi.getParameter("p_spec").trim();
		String p_content = multi.getParameter("p_content").trim();
		int p_point = Integer.parseInt(multi.getParameter("p_point").trim());
		int p_num = Integer.parseInt(multi.getParameter("p_num").trim());
		
		String p_image_new = multi.getFilesystemName("p_image_new");
		
		if(p_image_new == null) { // 수정할 첨부파일 이미지가 없는 경우
			p_image_new=multi.getParameter("p_image_old"); //old는 이전에 이미 getFilesystemName() 메서드를 이용했기 때문에 getParameter로 사용해도 된다.
		}
		ProductDTO dto = new ProductDTO();
		
		dto.setPnum(p_num);
		dto.setPcategory_fk(p_category);
		dto.setPname(p_name);
		dto.setPcompany(p_company);
		dto.setPqty(p_qty);
		dto.setPrice(p_price);
		dto.setPspec(p_spec);
		dto.setPcontents(p_content);
		dto.setPoint(p_point);
		dto.setPimage(p_image_new);
		
		ProductDAO dao = ProductDAO.getInstance();
		
		int check = dao.updateProduct(dto);
		
		PrintWriter out = response.getWriter();
		
		ActionForward forward = new ActionForward();
		
		if(check>0) {
			forward.setRedirect(true);
			forward.setPath("admin_product_list.do");
		}else {
			out.println("<script>");
			out.println("alert('제품 정보 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
			
		}
		return forward;
	}

}
