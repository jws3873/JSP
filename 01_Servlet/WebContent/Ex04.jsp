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
		<form method="get" action="profile">
			<p>이름 : <input type="text" name="name">  </p>
			<p>나이 : <input type="text" name="age">  </p>
			
			<input type="submit" value="전송">
		</form>
		
		<form method="post" action="profile1">
			<p>이름 : <input type="text" name="name">  </p>
			<p>나이 : <input type="text" name="age">  </p>
			
			<input type="submit" value="전송">
		</form>
		
	</div>

</body>
</html>