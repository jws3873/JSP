package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */

/*
 *  요청과 관련된 API : java.servelt.http.HttpServeletRequest 인터페이스
 *  응답과 관련된 API : java.servelt.http.HttpServeletResponse 인터페이스
 *  
 *  1. 클라이언트가 서블릿에 요청을 하면 먼터 톰캣 서버가 해당 요청을 받음.
 *  2. 그런 다음 사용자의 요청이나 응답에 대한 HttpServeletRequest 객체와
 *  	HttpServeletResponse 객체를 만들게 됨.
 *  3. 그리고 난 후 Servlet의 doGet() 메서드나 doPost() 메서드를 호출하면서
 *  	이 객체들을 전달하게 됨.
 *  4. 톰캣이 사용자의 요청에 대한 정보를 모든 HttpServeletRequest 객체의 속성으로
 *  	담아 메서드로 전달을 함. 따라서 HttpServeletRequest 에서 제공하는 메서드들은
 *  	매개변수로 넘어온 객체들을 이용하여 사용자가 전송한 데이터를 받아오거나 응답을 할 수
 *  	있는 것임.
 *  서블릿에서 클라이언트의 요청을 얻는 방법
 *  - HttpServeletRequest 객체에서 <form>태그로 전송된 데이터를 받아오는데
 *  사용이 되는 메서드.
 *  * getParameter(String name) : <form> 태그의 name속성에 들어간 변수명을
 *  							받아서 사용을 함. 반환형은 String 타입임.
 *  * getParameterValues(String name) : <form> 태그의 같은 name에
 *  									대하여 여러 개의 값을 얻을 때 사용함.
 *  									반환형은 String[] 배열 타입임.
 *  
 *   서블릿에서 요청받은 내용을 처리하여 클라이언트에 보내는 방법
 *   1. HttpServeletResponse 객체를 이용하여 응답을 함.
 *   2. doGet() 이나 doPost() 메서드 안에서 처리함.
 *   3. javax.servelt.http.HttpServeletResponse 객체를 이용함
 *   4. setCointentType() 메서드를 이용하여 클라이언트에 전송할
 *   	데이터의 종류(MIME_TYPE)를 지정함. 출력타입을 미리 지정하여 속도를 상승하기 위함
 *   5. 클라이언트(웹 브라우저)와 서블릿의 통신은 자바I/O의 스트림을 이용함.
 *   
 *   
 *   웹 브라우저에서 서블릿으로 데이터를 전송하는 방법 2가지
 *   1.  get 방식
 *   	- 서블릿에 데이터를 전송할 때는 데이터가  url 뒤에 name=value 형태로 전송이 됨.
 *   	- 여러 개의 데이터를 전송할 때는 '&' 로 구분하여 전송이 됨.
 *   	- 보안이 취약함.
 *   	- 전송할 수 있는 데이터는 최대 255자.
 *   	- 기본 전송 방식으로 사용이 쉬움.
 *   	- 웹 브라우저에 직접 입력해서 전송할 수도 있음.
 *   	- 서블릿에서는 doGet() 메서드를 전송된 데이터를 처리함.
 *   
 *   2. post 방식
 *   	- 서블릿에 데이터를 전송할 때는 TCP/IP 프로토콜 데이터의 head 영역에 
 *   	숨겨진 채 전송이 됨.
 *   	- 보안에 유리함
 *   	- 전송 데이터의 용량이 무제한임.
 *   	- 처리 속도가 get 방식보다는 느림.
 *   	- 서블릿에서는 doPost() 메서드에서 전송된 데이터를 처리함.
 *  
 */
@WebServlet("/login") // 맵핑하는 부분 병칭 정확히 맞추어 주어야 한다. 너무 길고, 보안에 노출되어 있는 경로를 간단하게 하는 것이 매핑입니다.

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// form 태그에서 method="get" 인 경우 
		
		/*
		 * request : - 첫번째 매개변수
		 * 			- 사용자(클라이언트)의 요청에 대한 정보를 처리 
		 * 
		 * response : - 두번째 매개변수
		 * 			  - 사용자의 요청 정보에 대한 처리 결과를 클라이언트에 응답 처리.
		 * 
		 * */
		
		// 1단계 : 클라이언트에서 넘어온 데이터를 받기 - 사용자가 전송한 데이터를 받기
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		
		System.out.println("입력한 아이디 >>> "+id);
		System.out.println("입력한 비밀번호 >>> "+pwd);
		
		// 응답 시에 한글이 나오는 경우 
		// 클라이언트로 응답 시 한글을 작성하면 한글이 깨지는 현상이 발생함.
		response.setContentType("text/html; charset=UTF-8");
		
		// 응답은 resopnse 객체를 이용한다.
		PrintWriter out =  response.getWriter();
		
		// 클라이언트에 응답을 해 주자.
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<h2> 입력한 아이디: "+id+"<br>");
		out.println("입력한 비밀번호: "+pwd+"</h2>");
		out.println("</body>");
		out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
