<%@page import="com.emp.model.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	EmpDTO cont = (EmpDTO)request.getAttribute("content");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	   <hr width="50%" color="tomato">
	      <h3>EMP 테이블 사원 상세 정보 화면</h3>
	   <hr width="50%" color="tomato">
	   <br> <br>
	   
	   <table border="1" cellspacing="0" width="400">
	      <%
	         if(cont != null) {
	      %>
	      	<tr>
	      	   <th>사원 No.</th>
	      	   <td> <%=cont.getEmpno() %> </td>
	      	</tr>
	      	
	      	<tr>
	      	   <th>사원명</th>
	      	   <td> <%=cont.getEname() %> </td>
	      	</tr>
	      	
	      	<tr>
	      	   <th>담당업무</th>
	      	   <td> <%=cont.getJob() %> </td>
	      	</tr>
	      	
	      	<tr>
	      	   <th>관리자 No.</th>
	      	   <td> <%=cont.getMgr() %> </td>
	      	</tr>
	      	
	      	<tr>
	      	   <th>급  여</th>
	      	   <td> <%=cont.getSal() %> </td>
	      	</tr>
	      	
	      	<tr>
	      	   <th>보너스</th>
	      	   <td> <%=cont.getComm() %> </td>
	      	</tr>
	      	
	      	<tr>
	      	   <th>부서번호</th>
	      	   <td> <%=cont.getDeptno() %> </td>
	      	</tr>
	      	
	      	<tr>
	      	   <th>입사일자</th>
	      	   <td> <%=cont.getHiredate() %> </td>
	      	</tr>
	         
	      <% }else { %>
	    	  
	    	  <tr>
	    	     <td colspan="2" align="center">
	    	        <h3>검색된 사원 정보가 없습니다.....</h3>
	    	     </td>
	    	  </tr>
	      <% } %>
	   
	      <tr> 
	         <td colspan="2" align="center">
	            <input type="button" value="사원수정"
	                onclick="location.href='update.do?no=<%=cont.getEmpno() %>'">
	            <input type="button" value="사원삭제"
	                onclick="if(confirm('사원을 삭제 하시겠습니까?')) {
	                			location.href='delete.do?no=<%=cont.getEmpno() %>'
	                		 }else {return; }">
	            <input type="button" value="사원목록"
	                onclick="location.href='select.do'">
	         </td>
	      </tr>
	   </table>

	</div>

</body>
</html>