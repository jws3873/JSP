<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
	<hr width="30%" color="skyblue">
	<h3>MEMBER10 테이블 회원 수정</h3>
	<hr width="30%" color="skyblue">
	<br>
	
	<form method="post" action="<%=request.getContextPath()%>/update_ok.do">
	<c:set var="dto" value="${Modify}"/>
	<input type="hidden" name = "mem_num" value="${dto.getNum()}">
	<input type="hidden" name="db_pwd" value="${dto.getPwd() }">
	
	<table border="1" cellspacing="0" width="350">
	<c:if test="${!empty dto }">
	<tr>
		<th>회원 아이디</th>
		<td>
			<input name ="mem_id" value="${dto.getMemid() }" readonly>
		</td>
	</tr>
	
	<tr>
		<th>회원 이름</th>
		<td>
			<input name = "mem_name" value="${dto.getMemname() }" readonly>
		</td>
	</tr>
	
	<tr>
		<th>회원 비밀번호</th>
		<td>
			<input type="password" name = "mem_pwd">
		</td>
	</tr>
	
	<tr>
		<th>회원 나이</th>
		<td>
			<input name = "mem_age" value="${dto.getAge() }">
		</td>
	</tr>
	
	<tr>
		<th>회원 마일리지</th>
		<td>
			<input name = "mem_mileage" value="${dto.getMileage() }">
		</td>
	</tr>
	
	<tr>
		<th>회원 직업</th>
		<td>
			<input name = "mem_job" value="${dto.getJob() }">
		</td>
	</tr>
	
	<tr>
		<th>회원 주소</th>
		<td>
			<input name = "mem_addr" value="${dto.getAddr() }">
		</td>
	</tr>
	</c:if>
	
	
	<c:if test="${empty dto}">
		<tr>
			<td colspan="2" align="center">
				<h3>검색된 데이터가 없습니다.</h3>			
			</td>
		</tr>
	
	
	</c:if>
	
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="회원 수정"> &nbsp;&nbsp;&nbsp;
			<input type="reset" value="다시 작성">
		</td>
	</tr>
	
	</table>
	
	
	</form>
	
	
	
	</div>
</body>
</html>