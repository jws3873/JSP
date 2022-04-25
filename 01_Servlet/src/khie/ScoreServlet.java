package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScoreServlet
 */
@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		String grade="";
		int total = kor+eng+math;
		double avg = Math.floor((((kor+eng+math)/3.0)*100))/100;
		if(avg>=90) grade = "A학점";
		else if(avg>=80) grade = "B학점";
		else if(avg>=70) grade = "C학점";
		else if(avg>=60) grade = "D학점";
		else grade = "F학점";
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<div align='center'>");
		out.println("<h2>");
		out.println("***"+name+"님 성적 결과***");
		out.println("</h2>");
		out.println("<h2>이름 : "+name+"<br>");
		out.println("<h2>국어점수 : "+kor+"점<br>");
		out.println("<h2>영어점수 : "+eng+"점<br>");
		out.println("<h2>수학점수 : "+math+"점<br>");
		out.println("<h2>총점 : "+total+"점<br>");
		out.println("<h2>평균 : "+avg+"점<br>");
		out.println("<h2>학점 : "+grade+"<h2>");
		
		
		
		out.println("<table border='1' cellspacing='0'width='300px'");
		out.println("<tr>");
		out.println("<th>");
		out.println("이름 : ");
		out.println("</th>");
		out.println("<td align='center'>");
		out.println(name);
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("국어점수 : ");
		out.println("</th>");
		out.println("<td align='center'>");
		out.println(kor+"점");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("영어점수 : ");
		out.println("</th>");
		out.println("<td align='center'>");
		out.println(eng+"점");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("수학점수 : ");
		out.println("</th>");
		out.println("<td align='center'>");
		out.println(math+"점");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("총점 : ");
		out.println("</th>");
		out.println("<td align='center'>");
		out.println(total+"점");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("평균 : ");
		out.println("</th>");
		out.println("<td align='center'>");
		out.println(avg+"점");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th>");
		out.println("학점 : ");
		out.println("</th>");
		out.println("<td align='center'>");
		out.println(grade);
		out.println("</td>");
		out.println("</tr>");

		out.println("</table>");
		out.println("</div>");
			
		
		out.println("</body>");
		out.println("</html>");
	}

}
