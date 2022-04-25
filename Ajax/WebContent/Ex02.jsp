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
		$.ajax({
			type:"post",
			url:"data/data.html",
			dataType:"html",
			success:function(data){
				document.body.innerHTML=data;
			},
			error:function(data){
				alert("통신에러");
			}
		});
	});
</script>

</head>
<body>

</body>
</html>