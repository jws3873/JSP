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
	<c:set var="dto" value="${reply }"/>
		<hr width="35%" color="red">
			<h3>${dto.getBoard_writer() }게시글 상세 내역 페이지</h3>
		<hr width="35%" color="red">
		<br>
		
		<form method="post" action="<%=request.getContextPath()%>/bbs_reply_ok.do">
		<input type="hidden" name="reply_no" value="${dto.getBoard_no() }">
		<input type="hidden" name="reply_group" value="${dto.getBoard_group() }">
		<input type="hidden" name="reply_step" value="${dto.getBoard_step() }">
		<input type="hidden" name="reply_indent" value="${dto.getBoard_indent() }">
		
		<table border="1" cellspacing="0" width="400"> 
			<tr>
				<th>작성자</th>
				<td>
					<input name="reply_writer" value="${dto.getBoard_writer() }">
				</td>
			</tr>
			
			<tr>
				<th>글제목</th>
				<td>
					<input name="reply_title" value="(Re)${dto.getBoard_title() }">
				</td>
			</tr>
			
			<tr>
				<th>글내용</th>
				<td>
					<textarea rows="7" cols="30" name = "reply_content">${dto.getBoard_cont() }</textarea>
				</td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="reply_pwd">
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="답변글"> &nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
				</td>
			</tr>
		
		
		</table>
		
		
		
		
		</form>		
	
	
	</div>
</body>
</html>