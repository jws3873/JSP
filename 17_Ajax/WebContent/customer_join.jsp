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
		// 회원 가입 폼 페이지에서 아이디 중복 체크라는 버튼에 
		// 마우스가 올라갔을 때 호출되는 무명함수
		$("#idcheck_btn").mouseover(function(){
			$("#idcheck").hide(); //span 태그 영역을 숨겨라.
			
			let userId = $("#member_id").val();
			
			// 입력 길이 체크
			if($.trim($("#member_id").val()).length<4){
				let warnningTxt = 
				'<font color = "red">아이디는 4자 이상어야 합니다.</font>';
				$("#idcheck").text('')  //id check 영역(span태그)을 초기화.
				$("#idcheck").show();  //id check 영역(span태그)을 초기화.
				$("#idcheck").append(warnningTxt);
				$("#member_id").val('').focus();
				return false;
			}
			
			if($.trim($("#member_id").val()).length>16){
				let warnningTxt = 
				'<font color = "red">아이디는 16자 이하어야 합니다.</font>';
				$("#idcheck").text('')  //id check 영역(span태그)을 초기화.
				$("#idcheck").show();  //id check 영역(span태그)을 초기화.
				$("#idcheck").append(warnningTxt);
				$("#member_id").val('').focus();
				return false;
			}
			
			// 아이디 중복 여부 확인 - Ajax 기술(비동기 통신)
			$.ajax({
				type: "post",
				url: "data/idCheck.jsp",
				data:{paramId : userId},
				datatype: "jsp",
				success: function(data){
					if(data==1){ // 중복인 경우
						let warnningTxt = 
							'<font color = "red">중복 아이디 입니다.</font>';
							$("#idcheck").text('')  //id check 영역(span태그)을 초기화.
							$("#idcheck").show();  //id check 영역(span태그)을 초기화.
							$("#idcheck").append(warnningTxt);
							$("#member_id").val('').focus();
							
					}else{ // 중복이 아닌 경우
						let warnningTxt = 
							'<font color = "red">사용 가능한 아이디 입니다.</font>';
							$("#idcheck").text('')  //id check 영역(span태그)을 초기화.
							$("#idcheck").show();  //id check 영역(span태그)을 초기화.
							$("#idcheck").append(warnningTxt);
					}
				},
				error: function(data){
					alert("통신 오류입니다.~~~~")
				}
			});
			
		});
	});


</script>
</head>
<body>
	
	<div align="center">
		<hr width="30%" color="red">
			<h3>회원 가입 폼 페이지</h3>
		<hr width="30%" color="red">
		<br>
		
		<form method="post" action="http:www.naver.com">
		
			<table border="1" cellspacing="0" width="450">
				<tr>
					<th>회원 아이디</th>
					<td>
						<input name="member_id" id="member_id" size="20">
						<input type="button" value="아이디 중복체크" id="idcheck_btn"> <br>
						<span id="idcheck"></span> 
					</td>
				</tr>
				
				<tr>
					<th>회원 이름</th>
					<td> <input name="member_name">  </td>
				
				</tr>
			
			
			</table>
		
		
		</form>
	</div>
	
</body>
</html>