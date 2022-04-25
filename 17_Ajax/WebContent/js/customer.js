/**
 * customer 테이블을 이용한 Ajax 실
 */

$(function(){
	// 여러 ajax에서 동일하게 사용되는 속성 설정
	$.ajaxSetup({
		//ajax에서 한글이 깨짐 문제 해결
		ContentType : "application/x-www-form-urlencoded;charset=UTF-8",
		type: "post"
	});
	
	// 모든 데이터를 가져오는 함수 - customer 테이블의 전체 데이터를 가져오는 함수
	function getData(){
		$.ajax({
			url:"/17_Ajax/select.do",
			datatype:"xml", //결과 데이터 타입
			success:function(data){
				// 테이블태그 첫번째 행(타이틀(제목) 태그)을 제외하고 나머지 
				// 나머지 모든 행을 제거하라는 의미.
				$("#listTable tr:gt(0)").remove();
				
				let table = "";
				
				$(data).find("customer").each(function() {
					table+="<tr>";
					table+="<td>"+$(this).find("no").text()+"</td>";
					table+="<td>"+$(this).find("id").text()+"</td>";
					table+="<td>"+$(this).find("name").text()+"</td>";
					table+="<td>"+$(this).find("age").text()+"</td>";
					table+="<td>"+$(this).find("phone").text()+"</td>";
					table+="<td>"+$(this).find("addr").text()+"</td>";
					table+="<td id='tdDel'> <input type='button' value='삭제'"+
							"id='del' num='"+ $(this).find("no").text()+"'></td>";
					table+="</tr>";
			});
				// 테이블의 첫번째 행의 아래에 table을 추가
				$("#listTable tr:eq(0)").after(table);
			},
			error:function(){
				alert('통신 에러 발생');
			}
		});
	} //getData() 함수의 end
	
	
	// 아이디 중복 여부 확인
	$("#id").keyup(function(){
		$.ajax({
			url: "/17_Ajax/idCheck.do",
			datatype: "text",
			data:{id : $("#id").val()},
			success:function(data){
				$("span").html(data);
			},
			error:function(){
				alert('통신 에러 발생');
			}
		});
	});// keyup()함수 end부분
	
	// 가입하기 버튼을 클릭했을 때 DB에 추가로 저장
	
	$("#btn").click(function(){
		$.ajax({
			url: "/17_Ajax/insert.do",
			datatype: "text",
			data: $("#inForm").serialize(), 
			success: function(data){
				if(data>0){
					alert('고객 가입 완료 !!!');
					
					// 가입 완료 후에 다시 전체 레코드를 화면에 뿌려주면 됨.
					getData();
					
					//input 태그에 입력된 내용을 지우는 작업
					$("input[type=text]").each(function(){
						$(this).val("");
					});
				}else{
					alert('고객 가입 실패 !!!');
				}
			},
			error : function(){
				alert('통신 에러 발생');
			}
		});
	});// 가입하기 end
	
	
	// 삭제 버튼을 클릭했을 때 이벤트 적용
	// 삭제 버튼은 동적으로 생성된 요소는 제이쿼리에서 on() 함수를 이용해야 함.
	// 형식) on("click이나 change 같은 이벤트", "이벤트 적용 선택자 또는 태그", 종작함수(무명함수))
	$("table").on("click","#del",function(){
		$.ajax({
			url: "/17_Ajax/delete.do",
			datatype:"text",
			data: "no="+$(this).attr("num"),
			success: function(data){
				if(data>0){
					alert('고객이 삭제되었습니다!!!');
					getData();
				}else{
					alert('고객 삭제 실패~~~');
				}
			},
			error: function(){
				alert('통신 에러 발생');
			}
		});
	});
	
	
	getData();
	
});