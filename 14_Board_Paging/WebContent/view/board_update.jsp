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
		<hr width="30%" color="skyblue">
		<h3>BOARD 테이블 게시물 수정 폼 페이지</h3>
		<hr width="30%" color="skyblue">
		
		<br>
	
	<form method="post" action="<%=request.getContextPath()%>/board_update_ok.do">
	<c:set var = "dto" value="${Modify}"/>
	<input type="hidden" name = "no" value="${dto.getBoard_no() }">
	<input type="hidden" name = "db_pwd" value="${dto.getBoard_pwd() }">
	<input type="hidden" name = "page" value="${page}">
	
	<table border="1" cellspacing="0" width="400">
	<c:if test="${!empty dto }">
		<tr>
			<th>작성자</th>
			<td>
			<input name="writer" value="${dto.getBoard_writer()}" readonly> 
			</td>
		</tr>
		
		<tr>
			<th>글제목</th>
			<td>
			<input name="title" value="${dto.getBoard_title()}" > 
			</td>
		</tr>
		
		<tr>
			<th>글 내용</th>
			<td>
				<textarea rows="7" cols="20" name="content">${dto.getBoard_cont() }</textarea>
			</td>
		</tr>
		
		<tr>
			<th>글 비밀번호</th>
			<td>
			<input type="password" name="pwd"> 
			</td>
		</tr>
		</c:if>

		<c:if test="${empty dto }">
			<tr>
				<td colspan="2" align="center">
					<h3>검색된 게시물이 없습니다....</h3>
				</td>
			</tr>
		</c:if>

		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="글 수정"> &nbsp;&nbsp;&nbsp;
				<input type="reset" value="다시 작성">
			</td>
		</tr>
		
		
	
	
	
	</table>
	</form>
	</div>
</body>
</html>