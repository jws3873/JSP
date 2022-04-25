<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- redirect 특성상 요청과 동시에 값이 사라진다 이때문에 nullpointer error발생이 자주 일어난다. -->
	<div align ="center">
		<hr width="30%" color="red">
			<h2>메인 페이지</h2>	
		<hr width="30%" color="red">
		<br>
		
		<h3><%=request.getParameter("id").trim() %>님 환영합니다. </h3>
		
		<h4>
		
		비밀번호 : <%=request.getParameter("pwd") %><br>
		이름 : <%=request.getAttribute("Name") %> <br>
		주소 : <%=request.getAttribute("Addr") %> <br>
		</h4>
		
	</div>
	
</body>
</html>