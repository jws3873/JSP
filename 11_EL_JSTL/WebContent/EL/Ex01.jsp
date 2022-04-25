<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int su = 150;

	pageContext.setAttribute("SU", su);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>]
	<h2>표현 언어로 여러가지 데이터 출력하기</h2>
	
	<h3>
		${100} <br>
		
		<%=su %> <br>
	
		EL >>> ${SU} <br>
		${SU} <br>

		${"안녕하세요"} <br>	

		${10+ 20}<br>	
	
	</h3>
	
</body>
</html>