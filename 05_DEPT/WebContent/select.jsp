
<%@page import="com.khie.model.DeptDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	List<DeptDTO> dept =(List<DeptDTO>)request.getAttribute("list");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="30%" color="red">
			<h3>DEPT 테이블 전체 리스트</h3>
		<hr width="30%" color="red">
		<br>
		
		<table border="1" cellspacing="0" width="350">
			<tr> 
				<th>부서No. </th> <th>부서명</th>
				<th>부서위치 </th> <th>부서 삭제</th>
			</tr>
		<%
			if(dept.size() !=0){ //데이터가 있다면
				// 데이터 수 만큼 반복해서 화면에 출력
				for(int i = 0; i<dept.size();i++){
					DeptDTO dto = dept.get(i);
					
					System.out.println("받은 dto>>>" +dto);
		%>
		<tr>
			<td><%=dto.getDeptno() %> </td>
			<td><%=dto.getDname() %> </td>
			<td><%=dto.getLoc() %> </td>
			<td> <input type="button" value="부서삭제"
					onclick="location.href='delete?deptno=<%=dto.getDeptno()%>'"> <!-- ?는 get방식을 의미한다. 뒤에 변수는 getparameter로 받을 수 있음 -->
		</tr>
		<%  } //for문 end
			}else { 
		%>
		<tr>
			<td colspan="4" align="center">
				<h3>검색된 레코드가 없습니다....</h3>
			</td>
		</tr>
		<% 
		}
		%>
		<tr>
			 <td colspan="4" align="center">
                 <input type="button" value="부서추가"
              onclick="location.href='insert.jsp'">
		</tr>

		</table>
		
	</div>
	

</body>
</html>