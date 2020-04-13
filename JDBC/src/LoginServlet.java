

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.web.dao.MemberDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberDAO dao; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        dao = new MemberDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.setAttribute("message", "로그아웃");
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("pw");
		
		String dbPw = dao.getPassword(userId);
		if(dbPw==null) {
			request.setAttribute("message", "아이디가 없습니다.");
		} else {
			if(dbPw.equals(userPw)) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", userId);
				response.sendRedirect("/JDBC/EmpIndex.jsp");
				return;
			} else {
				request.setAttribute("message", "비밀번호가 틀렸습니다.");
			}
		}
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
	}

}
