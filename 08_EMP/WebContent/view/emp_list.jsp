<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	List<EmpDTO> list = (List<EmpDTO>)request.getAttribute("List");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	   <hr width="50%" color="red">
	      <h3>EMP 테이블 전체 사원 리스트</h3>
	   <hr width="50%" color="red">
	   <br> <br>
	   
	   <table border="1" cellspacing="0" width="350">
	      <tr>
	         <th>사원 No.</th> <th>이 름</th>
	      	 <th>부서 No.</th> <th>입사일자</th>
	      </tr>
	      
	      <%
	         if(list.size() != 0) {  // 데이터가 있다면
	        	 // 데이터 수만큼 반복해서 출력
	        	 for(int i=0; i<list.size(); i++) {
	        		 EmpDTO dto = list.get(i);
	      %>
	      			<tr>
	      			   <td> <%=dto.getEmpno() %> </td>
	      			   <td> <a href="<%=request.getContextPath() %>/content.do?no=<%=dto.getEmpno() %>">
	      			   							<%=dto.getEname() %> </a></td>
	      			   <td> <%=dto.getDeptno() %> </td>
	      			   <td> <%=dto.getHiredate().substring(0, 10) %> </td>
	      			</tr>
	      <%  	 }  // for문 end
	         }else {
	      %>
	      			<tr>
	      			   <td colspan="4" align="center">
	      			      <h3>검색된 사원 목록이 없습니다.....</h3>
	      			   </td>
	      			</tr>  	 
	      <%   }  %>
	   
	   		<tr>
	   		   <td colspan="4" align="center">
	   		      <input type="button" value="사원등록"
	   		         onclick="location.href='insert.do'">
	   		   </td>
	   		</tr>
	   </table>
	
	</div>
</body>
</html>