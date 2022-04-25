<%@page import="com.products.model.ProductsDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ProductsDTO content = (ProductsDTO)request.getAttribute("content");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="skyblue">
		<h3>products 테이블 상품 상세정보 페이지</h3>
		<hr width="30%" color="skyblue">
		
		<table border="1" cellspacing="0" width="400">
		<%
			if(content != null){
		%>
			<tr>
				<th>제품No.</th>
				<td><%=content.getPnum()%></td>
			</tr>
			
			<tr>
				<th>제품 카테고리</th>
				<td><%=content.getCategory_fk()%></td>
			</tr>
			
			<tr>
				<th>제품명</th>
				<td><%=content.getProduct_name()%></td>
			</tr>
			
			<tr>
				<th>제품 코드</th>
				<td><%=content.getEp_code_fk()%></td>
			</tr>
			
			<tr>
				<th>제품 입고가</th>
				<td><%=content.getInput_price()%>원</td>
			</tr>
			
			<tr>
				<th>제품 출고가</th>
				<td><%=content.getOutput_price()%>원</td>
			</tr>
			
			<tr>
				<th>배송비</th>
				<td><%=content.getTrans_cost()%>원</td>
			</tr>
			
			<tr>
				<th>마일리지</th>
				<td><%=content.getMileage()%>포인트</td>
			</tr>
			
			<tr>
				<th>제조사</th>
				<%
				if(content.getCompany() ==null){
				%>
				<td></td>
				<%
				}else{
				 %>
				<td><%=content.getCompany()%></td>				 
				 <%
				 }
				 %>
			</tr>
		<%
			}else{
				
		%>
			<tr>
				<td colspan="2" align="center">
				<h3>검색된 데이터가 없습니다.</h3>
				</td>
			</tr>
		<%
			}
		 %>
		 	<tr>
		 		<td colspan="2" align="center">
		 			<input type="button" value="제품 수정"
		 				onclick="location.href='update.do?no=<%=content.getPnum()%>'">
		 			<input type="button" value="제품 삭제"
		 				onclick="if(confirm('정말로 제품을 삭제하시겠습니까?')){
		 					location.href='delete.do?no=<%=content.getPnum()%>'
		 				}else{return ;}">
		 				
		 			<input type="button" value="제품 목록"
		 				onclick="location.href='select.do'">
		 		</td>
		 	</tr>
		</table>
	
	</div>
</body>
</html>