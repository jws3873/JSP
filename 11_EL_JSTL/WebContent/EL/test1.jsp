<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<table border="1" cellspacing="0">
		<tr>
			<th>아이디</th>
			<td>${param.id}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${param.name}</td>
		</tr>
		<tr>
			<th>나이</th>
			<td>${param.age}</td>
		</tr>
		</table>
	</div>
</body>
</html>