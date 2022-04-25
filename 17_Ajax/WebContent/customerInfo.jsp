<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="js/customer.js"></script>
<style type="text/css">

   span{ width : 150px; color: red;}
   
   input{border: 1px solid gray;}
   
   table{width:85%;}
   
   .table_bg{background-color:pink;}
   
   th,td{border:1px solid gray; text-align:center; padding : 3px;}
   
</style>
</head>
<body>
	<%--서버에서 실행하는게 아니기 때문에 ojdbc8.jar 파일이 lib폴더에 필요하다!! --%>
   <div align="center">
      <hr width="85%" color="gray">
         <h3>회원 정보 입력 페이지</h3>
      <hr width="85%" color="gray">
      <br>
      
      <form name="inForm" id="inForm" method="post" >
         <table cellspacing="0">
            <tr class="table_bg">
               <th>아이디</th><th>이  름</th>
               <th>나  이</th> <th>전화번호</th> <th>주  소</th> 
            </tr>
            
            <tr>
               <td>
                  <input type="text" name="id" id="id" size="15">
                  <span>중복결과여부</span>
               </td>
               <td> <input type="text" name="name" id="name" size="15"> </td>
               <td> <input type="text" name="age" id="age" size="3"> </td>
               <td> <input type="text" name="phone" id="phone" size="20"> </td>
               <td> <input type="text" name="addr" id="addr" size="40"> </td>
            </tr>
            
            <tr>
               <td colspan="5" align="center">
                  <input type="button" value="가입하기" id="btn">
               </td>
            </tr>
         </table>
      </form>
      <br>
      
      <hr width="85%">
      <br>
      <h2>고객 리스트</h2>
      
      <table id="listTable" cellspacing="0">
         <tr>
            <th>번 호</th> <th>아이디</th> <th>이 름</th>
            <th>나 이</th> <th>전화번호</th> <th>주 소</th> <th>삭 제</th>
         </tr>
      </table>
   </div>

</body>
</html>