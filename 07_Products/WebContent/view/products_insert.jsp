<%@page import="com.products.model.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<CategoryDTO> category = (List<CategoryDTO>)request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="gray">	
		<h3>PRODUCTS 테이블 제품등록 폼</h3>
		<hr width="30%" color="gray">
		<br>
		
		<form method="post" action="<%=request.getContextPath()%>/insert_ok.do">
		
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>카테고리 코드</th>
				<td>
					<select name="product_category">
						<%
							if(category.size()!=0){
								for(int i = 0;i<category.size();i++){
									CategoryDTO dto = category.get(i);
						%>
							<option value="<%=dto.getCategory_code()%>"> 
								<%=dto.getCategory_name()%>[<%=dto.getCategory_code()%>]
							</option>
						
						<%
								}
							}else{
						%>
							<option value="">:::카테고리코드 없음::: </option>
						<%
							}
						%>
					</select>
				</td>
			</tr>
			
			<tr>
				<th>제품명</th>
				<td>
					<input type="text" name="product_name">
				</td>
			</tr>
			
			<tr>
				<th>제품코드</th>
				<td>
					<input type="text" name="product_code">
				</td>
			</tr>
			
			<tr>
				<th>제품 입고가</th>
				<td>
					<input type="text" name="product_input">
				</td>
			</tr>
			
			<tr>
				<th>제품 출고가</th>
				<td>
					<input type="text" name="product_output">
				</td>
			</tr>
			
			<tr>
				<th>제품 배송비</th>
				<td>
					<input type="text" name="product_transcost">
				</td>
			</tr>
			
			<tr>
				<th>제품 마일리지</th>
				<td>
					<input type="text" name="product_mileage">
				</td>
			</tr>
			
			<tr>
				<th>제품 제조사</th>
				<td>
					<input type="text" name="product_company">
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="제품등록"> &nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
				</td>
			</tr>
		</table>
		</form>	
	
	</div>

</body>
</html>