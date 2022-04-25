<%@page import="com.products.model.ProductsDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<ProductsDTO> products = (List<ProductsDTO>)request.getAttribute("list");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="30%" color="red">
		<h3>PRODUCTS 테이블 제품 전체 리스트</h3>
		<hr width="30%" color="red">
		
		<br>
		
		<table border="1" cellspacing="0" width="400">
		<tr>
			<th>제품번호</th> <th>카테고리코드</th>
			<th>제 품 명</th> <th>제 조 사</th> 
		</tr>
		
		<%
			if(products.size() != 0 ){
				for(int i = 0; i<products.size();i++){
				ProductsDTO dto = products.get(i);		
		%>		
					<tr>
						<td><%=dto.getPnum()%></td>
						<td><%=dto.getCategory_fk()%></td>
						<td> <a href="<%=request.getContextPath()%>/content.do?no=<%=dto.getPnum()%>">
						<%=dto.getProduct_name()%></a></td>
						<%
							if(dto.getCompany()==null){
						%>
							<td></td>	
						<%
							}else{
						%>
							<td><%=dto.getCompany()%></td>								
						<%		
							}
						%>
					</tr>
		<%
				}
			}else{
		%>
					<tr>
						<td colspan="4" align="center">
							<h3>검색된 데이터가 없습니다.</h3>
						</td>
					</tr>
		<%
			}
		%>
		
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="제품등록"
						onclick="location.href='insert.do'">
				</td>
			
			</tr>
		
		</table>
	
	</div>

</body>
</html>