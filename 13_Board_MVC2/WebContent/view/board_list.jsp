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
		<hr width="30%" color="marmoon">
		<h3>BOARD 테이블 전체 목록</h3>
		<hr width="30%" color="marmoon">
		<br>
		
		<table border="1" cellspacing="0" width="450">
		<tr>
			<th>게시판 글 번호</th> <th>게시판 글 제목</th> <th>게시판 글 작성자</th>
			<th>게시판 조회수</th> <th>게시판 글 작성자</th>
		</tr>
		
		<c:set var="list" value="${List}"/>
		<c:if test="${!empty list }">
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.getBoard_no()}</td>		
			<td> <a href="<%=request.getContextPath()%>/board_cont.do?no=${dto.getBoard_no()}">
			${dto.getBoard_title()}</a></td>		
			<td>${dto.getBoard_writer()}</td>		
			<td>${dto.getBoard_hit()}</td>		
			<td>${dto.getBoard_date().substring(0,10)}</td>		
		</tr>
		</c:forEach>
		</c:if>
		
		<c:if test="${empty list }">
		<tr>
			<td colspan="5"	align="center">
			<h3>검색된 데이터가 없습니다....</h3>
			</td>
		</tr>
		</c:if>
		 <tr>
		 <td colspan="5" align="right">
		 	<input type="button" value="글쓰기"
		 	onclick="location.href='board_write.do'">
		 </td>
		 </tr>
		</table>
		
		
		
		<hr width="30%" color="red">
		<br>
		<form method="post" action="board_search.do">
		<select name="search_field">
			<option value="title"> 제목 </option>
			<option value="content"> 내용 </option>
			<option value="title_content"> 제목+내용 </option>
			<option value="writer"> 작성자 </option>
		</select>
		
		<input type="text" name="search_keyword">
		<input type="submit" value="검색">
		
		</form>
	</div>
</body>
</html>