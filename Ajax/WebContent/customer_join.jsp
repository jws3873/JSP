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
		
		$("#idcheck_btn").mouseover(function(){
			$("#idcheck").hide();
			
			let usetId = $("#member_id").val();
			
			if($.trim($("#member_id").val()).length<4){
				
				let warnningTxt = '<font color = "red">아이디는 4자 이상어야 합니다.</font>';
				$("#idcheck").text('');
				$("#idcheck").show();
				$("#idcheck").append(warnningTxt);
				$("#member_id").val('').focus();
				return false;
			}
			
			if($.trim($("#member_id").val()).length>16){
				
				let warnningTxt = '<font color = "red">아이디는 16자 이하어야 합니다.</font>';
				$("#idcheck").text('');
				$("#idcheck").show();
				$("#idcheck").append(warnningTxt);
				$("#member_id").val('').focus();
				return false;
			}
			
			
			$.ajax({
				type:"post",
				url:"data/idCheck.jsp",
				data:{paramId:usetId},
				datatype: "jsp",
				success:function(data){
					if(data==1){
						let warnningTxt = '<font color = "red">아이디가 중복됩니다..</font>';
						$("#idcheck").text('');
						$("#idcheck").show();
						$("#idcheck").append(warnningTxt);
						$("#member_id").val('').focus();
						return false;
					}else{
						let warnningTxt = '<font color = "red">사용가능한 아이디입니다..</font>';
						$("#idcheck").text('');
						$("#idcheck").show();
						$("#idcheck").append(warnningTxt);
					}
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
					<td>
						<input name="member_name">
					</td> 
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>