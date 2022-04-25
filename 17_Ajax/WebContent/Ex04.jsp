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
			type: "get",
			url: "data/data.json",
			dataType: "json",
			success: function(data){
				// index : data 객체 내의 인덱스를 의미함.
				// item : data 객체 내의 이름과 값을 가지고 있는 객체를 말함
				$.each(data,function(index, item){
					let txt = "<li>책체목: "+item.title + "</li>" +
							  "<li>책저자: "+item.author + "</li>" +
							  "<li>책가격: "+item.price + "</li><hr>";
							
						$("body").append(txt);
				});
			},
			error: function(data){
				alert("데이터 통신 오류입니다.~~~");
			}
		});
	});

</script>
</head>
<body>

</body>
</html>