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
		<hr width="30%" color="red">
		<h3>BOARD 테이블 게시판 글쓰기 폼</h3>
		<hr width="30%" color="red">
	<br>
	
	<form method="post" action="<%=request.getContextPath()%>/board_write_ok.do">
	<table border="1" cellspacing="0" width="300">
	<tr>
		<th>작성자</th>
		<td>
		<input name="writer">
		</td>
	</tr>
	
	<tr>
		<th>글제목</th>
		<td>
		<input name="title">
		</td>
	</tr>
	
	<tr>
		<th>글제목</th>
		<td>
		<textarea rows="7" cols="20" name="content"></textarea>
		</td>
	</tr>
	
	<tr>
		<th>비밀번호</th>
		<td>
		<input type="password" name="pwd">
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="글쓰기"> &nbsp;&nbsp;&nbsp;
			<input type="reset" value="다시 작성">
		</td>
	</tr>
	
	</table>
	</form>	
	
	</div> 
</body>
</html>