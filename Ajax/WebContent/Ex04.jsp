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
			type:"get",
			url:"data/data.json",
			dataType:"json",
			success:function(data){
				$.each(data,function(index,item){
					let txt = "<li> 책체목:"+item.title+"</li>"+
							  "<li> 책저자:"+item.author+"</li>"+
							  "<li> 책가격:"+item.price+"</li><hr>";
					$("body").append(txt);
				});
			},
			erroer:function(data){
				alert("통신에러");
			}
			
			
		});
	});



</script>
</head>
<body>

</body>
</html>