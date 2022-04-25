<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<c:set var="dto" value="${Modify }"/>
	<hr width="30%" color="red">
	<h3>BOARD 테이블 ${dto.getBoard_writer() }님 게시물 수정 폼 페이지</h3>
	<hr width="30%" color="red">
	<br>
	
	<form method="post" action="<%=request.getContextPath()%>/board_update_ok.do">
	<input type="hidden" value="${page }" name="page">
	<input type="hidden" value="${dto.getBoard_no() }" name="no">
	<input type="hidden" value="${dto.getBoard_pwd() }" name="db_pwd">
	
	<table border="1" cellspacing="0" width="350">
	<tr>
		<th>작성자</th>
		<td>
		<input name="writer" value="${dto.getBoard_writer() }" readonly>
		</td>
	</tr>
	
	<tr>
		<th>글제목</th>
		<td>
		<input name="title" value="${dto.getBoard_title() }">
		</td>
	</tr>
	
	<tr>
		<th>글내용</th>
		<td>
		<textarea rows="7" cols="20" name="content">${dto.getBoard_cont() }</textarea>
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
			<input type="submit" value="글수정"> &nbsp;&nbsp;&nbsp;		
			<input type="reset" value="다시작성">		
		</td>
	</tr>
	
	
	
	</table>
	
	</form>
	
	
	
	</div>

</body>
</html>