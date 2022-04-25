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
		<hr width="35%" color="lightgray">
		<h3>${dto.getUpload_writer() } 님 자료실 게시물 수정 폼 페이지</h3>
		<hr width="35%" color="lightgray">
		<br>
		
		<form method="post" enctype= "multipart/form-data"
			action="<%=request.getContextPath()%>/upload_update_ok.do">
		<c:set var="dto" value="${Modify }"/>
		<input type="hidden" name="upload_no" value="${dto.getUpload_no() }">
		<table border="1" cellspacing="0" width="400">
		<tr>
			<th>작성자</th>
			<td>
				<input name="upload_writer" value="${dto.getUpload_writer() }" readonly>			
			</td>
		</tr>
		
		<tr>
			<th>글제목</th>
			<td>
				<input name="upload_title" value="${dto.getUpload_title() }">			
			</td>
		</tr>
		
		<tr>
			<th>글내용</th>
			<td>
				<textarea rows="7" cols="30" name="upload_content">${dto.getUpload_cont() }</textarea>		
			</td>
		</tr>
		
		<tr>
			<th>파일 첨부</th>
			<td>
				<input type="file" name="upload_file">
			</td>
		</tr>
		
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="upload_pwd">			
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