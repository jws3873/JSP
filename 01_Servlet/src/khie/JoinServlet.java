package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청한 데이터에 한글이 있으면 깨지는 현상이 발생함.
		request.setCharacterEncoding("UTF-8");
		
		// 응답하는 데이터에 한글이 깨지는 현상을 방지
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계 : Ex05.jsp 페이지에서 넘어온 데이터들을 처리해 주자.
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		String userName = request.getParameter("name");
		String userPhone = request.getParameter("phone");
		String userAddr = request.getParameter("addr");
		
		// 여러 개의 데이터가 넘어올 경우 사용하는 방법.
		String[] userHobby = request.getParameterValues("hobby");
		
		// 2단계 : 웹브라우저에 요청한 결과를 화면에 보여주자.
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<table>");
		out.println("<table border='1' cellspacing='0'>");
		out.println("<tr>");
		out.println("<th>");
		out.println("아이디");
		out.println("</th>");
		out.println("<td>");
		out.println(userId);
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("비밀번호");
		out.println("</th>");
		out.println("<td>");
		out.println(userPwd);
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("이름");
		out.println("</th>");
		out.println("<td>");
		out.println(userName);
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("연락처");
		out.println("</th>");
		out.println("<td>");
		out.println(userPhone);
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("주소");
		out.println("</th>");
		out.println("<td>");
		out.println(userAddr);
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("취미");
		out.println("</th>");
		out.println("<td>");
		
		for(int i=0;i<userHobby.length;i++) {
			out.println(userHobby[i]+"&nbsp;&nbsp;&nbsp;");
		}
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
