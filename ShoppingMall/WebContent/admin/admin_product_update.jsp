<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp"/>
	
	<div align="center">
		<hr width="65%" color="gray">
		<h3>SHOP_PRODUCTS테이블 제품 수정 폼 페이지</h3>
		<hr width="65%" color="gray">
		<br>	
	
	<form method="post" enctype="multipart/form-data" 
	action="<%=request.getContextPath()%>/admin_product_update_ok.do">
	<c:set var="dto" value="${productCont }"/>
	<input type="hidden" name="p_num" value="${dto.getPnum() }">
	<table border="1" cellspacing="0" width="600">
	<tr>
		<th>카테고리 코드</th>
		<td>
		 <input name="p_category" value="${dto.getPcategory_fk() }" readonly>  
		</td>
	</tr>
	
	<tr>
		<th>제품명</th>
		<td>
		 <input name="p_name" value="${dto.getPname()}" readonly> 
		</td>
	</tr>
	
	<tr>
		<th>제조사</th>
		<td>
		 <input name="p_company" value="${dto.getPcompany()}" readonly> 
		</td>
	</tr>
	
	<tr>
		<th>제품 이미지</th>
		<td>
		<img  src="<%=request.getContextPath()%>/upload/${dto.getPimage()}"
			width="100" height="80">
		<input type="file" name="p_image_new" >	
		<input type="hidden" name="p_image_old" value="${dto.getPimage() }">	
		</td>
	</tr>
	
	<tr>
		<th>제품 수량</th>
		<td>
		<input type="number" name="p_qty" min="1" max="100" value="${dto.getPqty() }">
		</td>
	</tr>
	
	<tr>
		<th>제품 가격 </th>
		<td>
		<input name="p_price" value="${dto.getPrice() }">
		</td>
	</tr>	
	
	<tr>
		<th>제품 사양</th>
		<td>
			<select name="p_spec">
					<option value="none"
					<c:if test="${dto.getPspec() == 'none' }"> selected </c:if> >일반</option>	
					<option value="hit"
					<c:if test="${dto.getPspec() == 'hit' }"> selected </c:if> >인기</option>	
					<option value="new"
					<c:if test="${dto.getPspec() == 'new' }"> selected </c:if> >최신</option>	
					<option value="recommand"
					<c:if test="${dto.getPspec() == 'recommand' }"> selected </c:if> >추천</option> 
				</select>
		</td>
	</tr>
	
	<tr>
		<th>제품 소개</th>
		<td>
			<textarea rows="7" cols="30" name="p_content" >${dto.getPcontents() }</textarea>
		</td>
	</tr>
	
	<tr>
		<th>제품 포인트 </th>
		<td>
		<input name="p_point" value="${dto.getPoint() }">
		</td>
	</tr>	
	
	<tr>
		<th>제품 포인트</th>
		<td>
		<input type="submit" value="제품수정"> &nbsp;&nbsp;&nbsp;
		<input type="reset" value="다시작성">
		</td>
	</tr>
	
	</table>
	
	
	</form>
	
	</div>
	<jsp:include page="../include/admin_bottom.jsp"/>
</body>
</html>