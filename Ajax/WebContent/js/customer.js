/**
 * 
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
	
	$("#id").keyup(function(){
		$.ajax({
			url:"/Ajax/idCheck.do",
			data:{id: $("#id").val()},
			datatype: "text",
			success:function(data){
				$("span").html(data);
			},
			error:function(){
				alert('통신 에러 발생');
			}
		});
	});
	
	$("#btn").click(function(){
		$.ajax({
			url:"/Ajax/insert.do",
			data: $("#inForm").serialize(), 
			datatype:"text",
			success:function(data){
				if(data>0){
					alert("고객 가입 완료");
					getData();
					
					$("input[type=text]").each(function(){
						$(this).val("");					
					});
				}
				else{
					alert("고객 가입  실패");
				}
			},
			error:function(){
				alert('통신 에러 발생');
			}
		});
	});
	
	
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
