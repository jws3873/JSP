<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".submit").on("click",function(){
			let data = $("form").serialize();
			$(".result").text(data);
			alert(data);
		});
		
	});

</script>
</head>
<body>
	<div align="center">
	
	<form action="">
		<table border="1" cellspacing="0" width="400">
		<tr>
			<td colspan="2" align="center">
				<h3>폼 데이터 쿼리 스트링을 변환</h3>
			</td>
		</tr>
		
		<tr>
			<th>아이디</th>
			<td>
				<input name="id">
			</td>
		</tr>
		
		<tr>
			<th>비밀번호</th>
			<td>
				<input type="password" name="pwd">
			</td>
		</tr>
		
		<tr>
			<th>나이</th>
			<td>
				<input name="age">
			</td>
		</tr>
		
		<tr>
			<th>연락처</th>
			<td>
				<input name="phone">
			</td>
		</tr>
		
		<tr>
			<td colspan="2" align="center"> 
						<input type="submit" class ="submit" value="전송"> &nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소">
			</td>
		</tr>
		</table>
	</form>
	<span class="result"></span>
	</div>
</body>
</html>