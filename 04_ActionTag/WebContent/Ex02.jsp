<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	
		<%-- <%@ include file="include/header.jsp" %> --%>
		<jsp:include page="include/header.jsp"/>
	
		<h2>본문 JSP페이지입니다.....</h2>
		<jsp:include page="include/footer.jsp"/>
	
		<%-- <%@ include file="include/footer.jsp" %> --%>
	
	</div>

</body>
</html>