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
	<hr width="30%" color = "red">	
	<h3>BOARD 테이블 게시글 상세 내역 페이지</h3>
	<hr width="30%" color = "red">	
	<table border="1" cellspacing="0" width="400">
	<c:set var="dto" value="${Content}"/>
	
	
	<c:if test="${!empty dto }">
	<tr>
	<td colspan="2" align="center">
		<h3>${dto.getBoard_writer()}님 게시글 상세내역</h3>
	</td>
	</tr>
	
	<tr>
		<th>작성자</th>
		<td>${dto.getBoard_writer()}</td>
	</tr>
	
	<tr>
		<th>글 제목</th>
		<td>${dto.getBoard_title()}</td>
	</tr>
	
	<tr>
		<th>글내용</th>
		<td>
		<textarea rows="7" cols="20">${dto.getBoard_cont()}</textarea>
		</td>
	</tr>
	
	<tr>
		<th>비밀번호</th>
		<td>
			<c:if test="${dto.getBoard_pwd().length()!=0 }">
				<c:forEach begin="1" end="${dto.getBoard_pwd().length()}">
					*				
				</c:forEach>
			</c:if>
		</td>
	</tr>
	
	<tr>
		<th>조회수</th>
		<td> ${dto.getBoard_hit()}</td>
	</tr>
	
	<tr>
		<c:if test="${ empty dto.getBoard_update() }">
		<th>작성일자</th>
		<td> ${dto.getBoard_date() }</td>
		</c:if>
		
		<c:if test="${ !empty dto.getBoard_update() }">
		<th>수정일자</th>
		<td> ${dto.getBoard_update() }</td>
		</c:if>
	</tr>
	</c:if>
	
	<c:if test="${empty dto }">
	<tr>
		<td colspan="2" align="center">
		<h3>검색된 데이터가 없습니다.....</h3>	
		</td>
	</tr>
	</c:if>
	
	<tr>
		<td colspan="2" align="center">
			<input type="button" value="글 수정"
				onclick="location.href='board_update.do?no=${dto.getBoard_no()}'">
			
			<input type="button" value="글 삭제"
				onclick="if(confirm('정말로 삭제하시겠습니까?')){
					location.href='board_delete.do?no=${dto.getBoard_no()}'
				}else{return ;}">
			
			<input type="button" value="전체목록"
				onclick="location.href='board_list.do'">
		</td>
	
	</tr>
	
	
	</table>
	
	</div>
</body>
</html>