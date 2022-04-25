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
			url: "data/book.xml",
			dataType: "xml",
			success: function(data){
				$(data).find("book").each(function(){
					let title = $("title",this).text();
					let author = $("author",this).text();
					let price = $("price",this).text();
					
					let txt = "<li> 책 제목: "+title+"</li>"+
							  "<li> 책 저자: "+author+"</li>"+
							  "<li> 책 가격: "+price+"</li><hr>";
					$("body").append(txt);
				});
			},
			error: function(){
				alert("에러가 발생하였습니다.")
			}
		});
	});



</script>
</head>
<body>

</body>
</html>