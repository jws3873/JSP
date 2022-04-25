<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="35%" color="lightgray">
		<h3>UPLOAD 테이블 자료실 게시판 게시글 입력 폼 페이지</h3>
		<hr width="35%" color="lightgray">
		<br>
	
	<%-- enctype : 이진 파일을 업로드 하기 위한 속성 
	라이브러리 파일 다운 page = http://www.servlets.com/
	COS File Upload Library(왼쪽바) -> com.oreilly.servlet(왼쪽바) -> cos-20.08.zip(페이지 하단) -> 라이브러리 파일 복사 후 lib폴더에 복사 붙여넣기할것
	--%>
	<form method="post" enctype= "multipart/form-data"
	action="<%=request.getContextPath()%>/upload_write_ok.do">
	
	<table border="1" cellspacing="0" width="400">
	<tr>
		<th>작성자</th>
		<td>
			<input name="upload_writer">
		</td>
	</tr>
	
	<tr>
		<th>글제목</th>
		<td>
			<input name="upload_title">
		</td>
	</tr>
	
	<tr>
		<th>글내용</th>
		<td>
			<textarea rows="7" cols="30" name="upload_content"></textarea>
		</td>
	</tr>
	
	<tr>
		<th>파일첨부</th>
		<td>
			<input type="file" name="upload_file">
		</td>
	</tr>
	
	<tr>
		<th>비밀번호</th>
		<td>
			<input type="password" name="upload_pwd">
		</td>
	</tr>
	
	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="글쓰기">&nbsp;&nbsp;&nbsp;
			<input type="reset" value="다시 작성">
		</td>
	</tr>
	</table>
	</form>
	</div>
</body>
</html>