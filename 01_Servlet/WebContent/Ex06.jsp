<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		[문제] 성적 처리 폼 페이지 만들기
			이름, 국어점수, 영어점수, 수학점수를 
			입력받아서 성적처리한 내용을 
			웹 브라우저에 출력해 보세요. 총점 평균 학점
	 --%>
	 
	 <div align="center">
	 
	 	<form action="student">
	 		<hr width="50%" color="gray">
					<h2>성적 처리 폼 페이지 get</h2>
	 		<hr width="50%" color="gray">
	 		<table border ="1" cellspacing="0">
	 		<tr>
	 			<th>이름:</th>
	 			<td> <input type="text" name="name"></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>국어점수:</th>
	 			<td> <input type="text" name="kor"></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>영어점수:</th>
	 			<td> <input type="text" name="eng"></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>수학점수:</th>
	 			<td> <input type="text" name="math"></td>
	 		</tr>
	 		
	 		<tr>
				<td colspan="2" align="center">
					<input type="submit" value="전송">&nbsp;&nbsp;&nbsp;	
					<input type="reset" value="취소">
				</td>
			</tr>
			</table>
		</form>
		
		<form method="post" action="score">
	 		<hr width="50%" color="gray">
				<h2>성적 처리 폼 페이지 post</h2>
	 		<hr width="50%" color="gray">
	 		<table border ="1" cellspacing="0">
	 		<tr>
	 			<th>이름:</th>
	 			<td> <input type="text" name="name"></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>국어점수:</th>
	 			<td> <input type="text" name="kor"></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>영어점수:</th>
	 			<td> <input type="text" name="eng"></td>
	 		</tr>
	 		
	 		<tr>
	 			<th>수학점수:</th>
	 			<td> <input type="text" name="math"></td>
	 		</tr>
	 		
	 		<tr>
				<td colspan="2" align="center">
					<input type="submit" value="전송">&nbsp;&nbsp;&nbsp;	
					<input type="reset" value="취소">
				</td>
			</tr>
			</table>
		</form>
	 </div>

</body>
</html>