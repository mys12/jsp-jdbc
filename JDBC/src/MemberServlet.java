

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.dao.MemberDAO;
import lab.web.vo.MemberVO;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/Member.do")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    MemberDAO dao;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        dao = new MemberDAO();
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
		String userId = request.getParameter("userId");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		MemberVO member = new MemberVO();
		member.setUserId(userId);
		member.setName(name);
		member.setPassword(pw);
		member.setEmail(email);
		member.setAddress(address);
		
		dao.insertMember(member);
		request.setAttribute("message", "회원가입 완료");
		request.getRequestDispatcher("/Login.jsp").forward(request, response);
		
	}

}
