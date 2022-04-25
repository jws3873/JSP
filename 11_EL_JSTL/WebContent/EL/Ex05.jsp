<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		int k = 45+71;
	
		pageContext.setAttribute("k", k);
		// k키 값은 해당 페이지 내에서만 유효하다. 다른페이지로 이동시 유효하지 않은 값이다.
		
		request.setAttribute("R", 1000); // 주면 끝
		
		session.setAttribute("S", 10000); // 일정시간 이후 끝
		
		application.setAttribute("A", 10000000); //톰캣서버가 살아있으면 된다.
		
		RequestDispatcher rd = request.getRequestDispatcher("Ex06.jsp");

		rd.forward(request, response);
	
	%>
	
	<h2>
		결과 >>><%=k %> <br>
		결과(EL) >> ${k} <br> 
	</h2>
</body>
</html>