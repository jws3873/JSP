<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<BoardDTO> search = (List<BoardDTO>)request.getAttribute("Search");
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
		<h3>BOARD 테이블 검색 게시물 목록 </h3>
		<hr width="30%" color="red">
		<br>
		
		<table border="1" cellspacing="0" width="450">
			<tr>
				<th>게시판 글 번호</th>
				<th>게시판 글 제목</th>
				<th>게시판 글 작성자</th>
				<th>게시판 조회수</th>
				<th>게시판 글 작성일자</th>
			</tr>
			
			<%
			if(search != null){ //검색된 게시물이 있는 경우
				for(int i =0;i<search.size();i++){
				BoardDTO dto = search.get(i);
			%>

			<tr>
				<td><%=dto.getBoard_no()%></td>
				<td> <a href="<%=request.getContextPath()%>/content.do?no=<%=dto.getBoard_no()%>">
				<%=dto.getBoard_title()%></a></td>
				<td><%=dto.getBoard_writer()%></td>
				<td><%=dto.getBoard_hit()%></td>
				<td><%=dto.getBoard_date().substring(0,10)%></td>
			</tr>

			<%
				} //for문 end
			}else{ // 검색 된 게시물이 없는 경우
			%>
				<tr>
				 <td colspan="5" align="center"> 
				 	<h3>조회할 데이터가 없습니다.</h3>
				 </td>
				</tr>
			<%
			}
			%>
			
			<tr>
				 <td colspan="5" align="right"> 
				 	<input type="button" value="전체 게시물"
				 		onclick="location.href='select.do'">
				 </td>
			</tr>
			
		
		</table>

	</div>
</body>
</html>