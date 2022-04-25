<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

function process(){
	
	$.ajax({
		type: "get",
		url: "data/test.jsp",
		data: {param : "Hello Ajax!!!"},
		dataType: "text",
		success: function(data){
			$(".message").append(data);
		},
		error: function(data){
			alert("에러가 발생하였습니다.~~~~")
		}
	});
	
}

</script>
</head>
<body>
	<div align="center">
		<input type="button" value="전송하기"
			onclick="process()">
		<br> <br>
		
		<div class="message"></div>
	
	
	</div>
</body>
</html>