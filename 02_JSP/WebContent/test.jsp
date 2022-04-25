<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>

	<div align= "center">
		<hr width="30%" color="orange">
		 	<h2>페이지 이동</h2>
		 <hr width="30%" color="orange">
		 
		 <br>
		 <h2>페이지 이동 (forward)</h2>
		 <form method="post" action="forward2">
		 	<p>아이디 : <input type="text" name="id"> </p>
		 	<p>비밀번호 : <input type="password" name="pwd"> </p>
		 	<input type="submit" value="로그인">
		 </form>
		 
		 <hr width="30%" color="orange">
		 
		 <br>
		 <h2>페이지 이동 (redirect)</h2>
		 <form method="post" action="redirect2">
		 	<p>아이디 : <input type="text" name="id"> </p>
		 	<p>비밀번호 : <input type="password" name="pwd"> </p>
		 	<input type="submit" value="로그인">
		 </form>

	</div>
	


</body>
</html>