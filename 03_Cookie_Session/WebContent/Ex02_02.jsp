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
		String userId = request.getParameter("id").trim();
	
		String userPwd = request.getParameter("pwd").trim();
		
		// Ex02_01.jsp 페이지에서 넘어온 session정보도 받을 수가 있다.
		String sessionID = (String)session.getAttribute("ID");
		String sessionPWD = (String)session.getAttribute("PWD");
		String sessionNAME = (String)session.getAttribute("NAME");
	%>
	
	<h2>입력 폼(Ex02.jsp)에서 넘어온 데이터</h2>
	<h3>
		입력받은 아이디 : <%=userId%><br>

		입력받은 비밀번호 : <%=userPwd%><br>

	</h3>
	
	<hr>
	<h2>세션으로 넘어온 데이터</h2>
	<h3>
		세션으로 받은 아이디 : <%=sessionID %><br>
		세션으로 받은 비밀번호 : <%=sessionPWD %><br>
		세션으로 받은 이름 : <%=sessionNAME %><br>
	</h3>
	
	<script type="text/javascript">
	
		location.href="Ex02_03.jsp";
	
	</script>
	
</body>
</html>