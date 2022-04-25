<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	//Ex05.jsp 페이지에 넘어온 데이터들을 받아 주어야 한다.
	// trim() : 맨 앞과 맨 뒤에 공백이 존재하면 공백을 제거해주는 메서드.
	String userId = request.getParameter("id").trim();
	String userPwd = request.getParameter("pwd").trim();
	String userName = request.getParameter("name").trim();
	String userGender = request.getParameter("gender").trim();
	String userAddr = request.getParameter("addr").trim();
	String userPhone = request.getParameter("phone").trim();
	String userEmail = request.getParameter("email").trim();
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
			<h2>가입 회원 정보</h2>
		<hr width="30%" color="red">
		<table border = "1" cellspacing="0">
			<tr> 
				<th>아이디: </th>
				<td> <%=userId%></td>
			</tr>
			
			<tr> 
				<th>비밀번호: </th>
				<td> <%=userPwd%></td>
			</tr>
			
			<tr> 
				<th>이름: </th>
				<td> <%=userName%></td>
			</tr>
			
			<tr> 
				<th>성별: </th>
				<td> <%=userGender%></td>
			</tr>
			
			<tr> 
				<th>거주지: </th>
				<td> <%=userAddr%></td>
			</tr>
			
			<tr> 
				<th>연락처: </th>
				<td> <%=userPhone%></td>
			</tr>
			
			<tr> 
				<th>이메일: </th>
				<td> <%=userEmail%></td>
			</tr>
		</table>	
	
	</div>

</body>
</html>