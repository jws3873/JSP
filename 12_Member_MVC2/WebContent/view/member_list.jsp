<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- 인터넷에 연결되어 있어야 한다. --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="red">
			<h3>MEMBER10 테이블 전체 회원 리스트</h3>
		<hr width="30%" color="red">
		<br>
		
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>회원No.</th> <th>회원 아이디</th>
				<th>회원명</th> <th>가입일자</th>
			</tr>
			<c:set var="list" value="${List }"/>
			
			<c:if test="${!empty list}"> 
			<%-- <c:if test="${list.size()>0}">  --%>
				<c:forEach items="${list }" var="dto">				
					<tr>
						<td> ${dto.getNum() }</td>
						<td> ${dto.getMemid() }</td>
						<td> <a href="<%=request.getContextPath()%>/content.do?num=${dto.getNum()}">
						 ${dto.getMemname() }</a></td>
						<td> ${dto.getRegdate().substring(0,10) }</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>검색된 회원 리스트가 없습니다......</h3>		
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td colspan="4" align="right">
					<input type="button" value = "회원 등록"
						onclick = "location.href='insert.do'">
				</td>
			
			</tr>
		</table>
		
	</div>
</body>
</html>