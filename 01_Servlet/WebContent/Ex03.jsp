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
		<h2>두 수 더하기(에노테이션(1:1) 등록)</h2>
		<form method = "get" action="adder">
			<p>첫번째 수 : <input type="text" name="num1"> </p>
			<p>두번째 수 : <input type="text" name="num2"> </p>
		
			<input type="submit" value="계산">
		</form>
		
		<hr>
		
		<h2>두 수 더하기(web.xml 파일에 등록)</h2>
		<form method = "post" action="adder1">
			<p>첫번째 수 : <input type="text" name="num1"> </p>
			<p>두번째 수 : <input type="text" name="num2"> </p>
		
			<input type="submit" value="계산">
		</form>
	
	</div>
	
</body>
</html>