package khie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForwardServlet
 */
@WebServlet("/forward")
public class ForwardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForwardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// method = "get" 이나 method = "post" 다 받을 수 있는 메서드
		
		String userId = request.getParameter("id").trim();

		String userPwd = request.getParameter("pwd").trim();
		
		// 원래는 DB의 회원관리 테이블에서 입력한 아이디와 비밀번호가 맞는지
		// 확인을 하여 회원이면 메인페이지로 이동.
		
		String dbId = "hong";
		String dbPwd ="1234";
		
		if(userId.equals(dbId)){ // 입력폼 창에서 입력한 아이디와 DB상의 아이디가일치하는 경우
			if(userPwd.equals(dbPwd)){ // 입력폼 창에서 입력한 비밀번호 와 DB상의 비밀번호가 일치 하는 경우
				// 회원이 경우
				// 회원인 경우 메인 페이지로 이동 ==> 페이지 이동
				// 정보를 이동하는 페이지로 전달하는 방법
				request.setAttribute("Name", "홍길동");
				request.setAttribute("Addr", "서울시 중구 남대문로"); //정보를 넣어야지 07에서 받을 수 있다.
				
				RequestDispatcher rd = request.getRequestDispatcher("Ex11.jsp");
				
				rd.forward(request, response);
			}else { //아이디는 맞지만 비밀번호가 틀린 경우
				System.out.println("비밀번호가 틀립니다. 확인해 주세요");
				
			}
		}else {
			System.out.println("아이디를 확인해 주세요");
		}
		
	}

}
