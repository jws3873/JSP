<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<hr width="30%" color="gray">
		<h3>커피 주문 페이지</h3>
		<hr width="30%" color="gray">
		<br>
		
		<form method="post" action="test2.jsp">
			<table border="1" cellspacing="0" width="350">
				<tr>
					<th>커피 종류</th>
					<td>
					<select name="coffee_str">
						<option value="1">아메리카노-3,000원</option>
						<option value="2">카페라떼-4,000원</option>
						<option value="3">카푸치노-4,500원</option>
						<option value="4">카라멜 마끼아또-4,500원</option>
					</select>
					</td>
				</tr>
				
				<tr>
					<th>수량</th>
					<td>
					<input type="number" min="1" max="20" name="su">
					</td>
				</tr>
			<tr>
			<td colspan="2" align="center">
			<input type="submit" value="계산"> &nbsp;&nbsp;&nbsp;
			<input type="reset" value="취소"> 
			</td>
			
			</tr>
			
			
			</table>
		
		</form>
	</div>
	
</body>
</html>