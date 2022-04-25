<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--JSP구성요소
		1. 지시어(디렉티브) : JSP 페이지에 대한 설정 정보를 지정하는 공간.
		 - jsp 컨테이너에 해당 페이지의 특수한 처리 정보를 넣고자 할 때 사용한다.
		  예를 들어 사용할 스크립팅언어를 지정하거나, 다른 페이지의 컨텐트를 삽입하고, 커스텀 태그 라이브러리를 지정할 때 사용된다.

		 <%@ page %> : - JSP 페이지에 대한 정보를 지정하는 공간.
		 			   - 어떻게 처리해야 하는지, 전달하기 위한 내용도 담고 있는 공간.
					   - 클라이언트의 요청에 JSP 페이지가 실행될 때 필요한 정보를
					  	 JSP 컨테이너(톰캣)에게 알려주는 역할을 함.
		2. <%@ include %> : - 현재 페이지에서 다른 문서(JSP, HTML)를 가져와서 내용을
							컴파일 할때 사용되는 디렉티브.
							형식) <%@ include file = "포함할 파일의 url경로" %>
							- include 지시어를 사용한 JSP 페이지가 컴파일 되는 과정에서
							  include 되는 JSP 페이지의 소스 내용을 그대로 포함해서 컴파일을 진행함.
							- 즉, 복사 & 붙여넣기 방식으로 두개의 파일이 하나의 파일로
							   구성이 된 후 같이 컴파일이 됨.
							   
		3. <%@ taglib %> : - 사용할 태그 라이브러리를 지정하는 디렉티브
						   - EL / JSTL 언어 사용 시 적용되는 디렉티브
						   (추후 수업 진행 예정)
						   
		===================================================================================
		
		1. 페이지 언어(디렉티브)
		<%@ page language="java" contentType="text/html; charset=UTF-8"
   			 pageEncoding="UTF-8"%>
   			 
   			- language = "java" : 해당 JSP 페이지에서 사용되는 언어(java).
   			- contentType : 문서의 타입.
   							==> JSP 페이지의 내용을 어떤 형태로 출력할지를 웹브라우저에게 알려주는 역할.
   			- charset : 문자(한글) 설정(UTF-8, EUC-KR)
   			- import : 다른 패키지에 있는 클래스를 가져다가 사용할 때 지정.
   			- session : HttpSession 속성 의 사용 여부를 지정.
   			형식) <%@ page session = " true %>
   			- isErrorPage : 에러 페이지인지의 여부를 지정.
   			- errprPage : 에러가 발생했을 때 보여줄 에러페이지를 지정.
   			- pageEncoding="UTF-8" : 현재 페이지의 문자(한글) 설정.
	 --%>

		<%-- ★★★★★★★★★★★★★★★★★★★
			JSP 페이지의 구성 요소
			1. 스크립틀릿 : - 가장 일반적으로 JSP 페이지에서 많이 쓰이는 스크립트 요소.
						 - 주로 프로그래밍의 로직을 기술할 때 많이 사용이 됨.
						 - JSP 페이지에서 자바 코드가 작성되는 공간.
						 형식) <%
						 			자바코드;
						 	  %>
						 	  
			2. 표현식: - 일반적으로 JSP 페이지에서 자바의 System.outprintln()과 
						유사하게 사용이 됨. 데이터를 출력할 대 주로 사용이 됨.
						형식) <%=변수명 또는 수식%>
			
			3. 선언문 : - 일반적으로 JSP 페이지에서 자바의 멤버변수 또는 멤버 메서드를 
						선언할 때 사용이 됨.
						형식) <%!
								변수선언 또는 메서드 선언
							  %>
		 --%>
		 
		 <%
		 	// 자바 코드가 작성되는 공간 
		 	int su = 10;
		 
		 	System.out.println("su >>> "+su); //콘솔창에 출력이 됨
		 	
		 	Calendar cal = Calendar.getInstance();
		 	
/* 		 	int year = cal.get(Calendar.YEAR);
		 	
		 	int month = cal.get(Calendar.MONTH)+1;
		 
		 	int day = cal.get(Calendar.DAY_OF_MONTH); */
		 	
		 %>
		 
		 <%-- JSP표현식 : 변수의 값 또는 메서드의 결과값을 출력하는 공간. --%>
		 
		 <h2>
		 		오늘 날짜 : <%=cal.get(Calendar.YEAR)%>년 
		 				 <%=cal.get(Calendar.MONTH)+1%>월
		 				 <%=cal.get(Calendar.DAY_OF_MONTH)%>일
		 </h2>
		 
		 <hr>
		 
		 <h2>1부터 100까지의 합을 구해 보자.</h2>
		 <%
		 	int sum=0;
		 	for(int i =1;i<=100;i++){
		 		sum+=i;
		 	}
		 %>
		 <h2>1부터 100까지의 합 : <%=sum%></h2>
		 
		 <hr>
		 
		 <%!
		 	// 자바에서 변수 선언 및 메서드를 선언하는 공간 (대부분 스클립스에서 처리한다)
		 	public int plus(int su1, int su2){
			 return su1+su2;
		 }
		 %>
		 
		 <h2>15 + 76 : <%=plus(15,76)%></h2>
		 
		 
</body>
</html>