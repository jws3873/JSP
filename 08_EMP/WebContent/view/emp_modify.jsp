<%@page import="com.emp.model.DeptDTO"%>
<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	List<String> jobList = (List<String>)request.getAttribute("jList");

	List<EmpDTO> mgrList = (List<EmpDTO>)request.getAttribute("mList");
	
	List<DeptDTO> deptList = (List<DeptDTO>)request.getAttribute("dList");
	
	EmpDTO dto = (EmpDTO)request.getAttribute("modify");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	   <hr width="50%" color="gray">
	      <h3>EMP 테이블 사원 정보 수정 폼</h3>
	   <hr width="50%" color="gray">
	   <br> <br>
	   
	   <form method="post"
	      action="<%=request.getContextPath() %>/updateOk.do">
	      
	      <table border="1" cellspacing="0" width="350">
	         <tr>
	            <th>사원 No.</th>
	            <td> <input type="text" name="num"
	                     value="<%=dto.getEmpno() %>" readonly> </td>
	         </tr>
	         
	         <tr>
	            <th>사원명</th>
	            <td> <input type="text" name="name"
	                     value="<%=dto.getEname() %>" readonly> </td>
	         </tr>
	         
	         <tr>
	            <th>담당업무</th>
	            <td>
	               <select name="job">
	                  <% 
	                    if(jobList.size() == 0) {
	                  %>
	                  	<option value="">:::담당업무 없음:::</option>
	                  <% }else {  // 담당업무가 있다면
	                	  for(int i=0; i<jobList.size(); i++) {
	                		  String str = jobList.get(i);
	                		  
	                		  if(dto.getJob().equals(str)) {
	                %>
			                	<option value="<%=str %>" selected>
			                			<%=str %> </option>
	                <%		  }else { %>
			                	<option value="<%=str %>">
			                			<%=str %> </option>
	                <% }
	                	  }
	                  }
	                 %>
	               </select>
	            </td>
	         </tr>
	         
	         <tr>
	            <th>관리자 No.</th>
	            <td>
	               <select name="mgr">
	                  <% 
	                    if(mgrList.size() == 0) {
	                  %>
	                  	<option value="">:::관리자 없음:::</option>
	                  <% }else {  // 담당업무가 있다면
	                	  for(int i=0; i<mgrList.size(); i++) {
	                		  EmpDTO dto1 = mgrList.get(i);
	                		  
	                		  if(dto.getMgr() == dto1.getEmpno()) {
	                %>
			                	<option value="<%=dto1.getEmpno() %>" selected>
			                			<%=dto1.getEname() %> [<%=dto1.getEmpno() %>] </option>
	                <%		  }else { %>
			                	<option value="<%=dto1.getEmpno() %>">
			                			<%=dto1.getEname() %> [<%=dto1.getEmpno() %>] </option>
	                <% }
	                	  }
	                  }
	                 %>
	               
	               </select>
	         	</td>
	         </tr>
	         
	         <tr>
	            <th>급 여</th>
	            <td> <input type="text" name="sal"
	                    value="<%=dto.getSal() %>"> </td>
	         </tr>
	         
	         <tr>
	            <th>보너스</th>
	            <td> <input type="text" name="comm"
	                    value="<%=dto.getComm() %>"> </td>
	         </tr>
	         
	         <tr>
	            <th>부서번호</th>
	            <td>
	               <select name="deptno">
	                  <% 
	                    if(deptList.size() == 0) {
	                  %>
	                  	<option value="">:::부서번호 없음:::</option>
	                  <% }else {  // 부서번호가 있다면
	                	  for(int i=0; i<deptList.size(); i++) {
	                		  DeptDTO dto1 = deptList.get(i);
	                		  
	                		  if(dto.getDeptno() == dto1.getDeptno()) {
	                  %>
			                	<option value="<%=dto1.getDeptno() %>" selected>
			                			<%=dto1.getDname() %> [<%=dto1.getDeptno() %>] </option>
	                  <%	  }else { %>
			                	<option value="<%=dto1.getDeptno() %>">
			                			<%=dto1.getDname() %> [<%=dto1.getDeptno() %>] </option>
	                  <% }
	                	    }
	                     }
	                  %>
	               
	               </select>
	         	</td>
	         </tr>
	      
	      	 <tr>
	      	    <td colspan="2" align="center">
	      	       <input type="submit" value="사원수정">&nbsp;&nbsp;&nbsp;
	      	       <input type="reset" value="다시작성">
	      	    </td>
	      	 </tr>
	      
	      </table>
	   
	   
	   </form>
	
	
	</div>





</body>
</html>