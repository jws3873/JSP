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
		<hr width="=30%" color="blue">
			<h3>DEPT 테이블 메인 페이지</h3>
		<hr width="=30%" color="blue">
		
		<br>
		<!-- request.getContextPath() : 현재 프로젝트명을 문자열로 반환해 주는 메서드 05_DEPT가 반환된다.-->
		<a href="<%=request.getContextPath()%>/select">[전체 부서 목록]</a><!-- a태그를 이용해서 맵핑하는 방법이다. servlet을 주로 컨트롤러 라고 한다.-->
		
		
	</div>

</body>
</html>