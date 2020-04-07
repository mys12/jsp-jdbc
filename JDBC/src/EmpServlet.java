

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.dao.EmpDAO;
import lab.web.vo.EmpVO;

/**
 * Servlet implementation class EmpServlet
 */
@WebServlet("/Emp.do")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	EmpDAO dao;
	
	
    @Override
	public void init(ServletConfig config) throws ServletException {
		dao = new EmpDAO();
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public EmpServlet() {
        super();
    }
    	
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String url = "";
		if("search".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			EmpVO emp = dao.selectEmployee(empId);
			request.setAttribute("emp", emp);
			url = "/EmpView.jsp";
		}else if("list".equals(action)) {
			request.setAttribute("list", dao.selectEmployeeList());
			url = "/EmpList.jsp";
		}else if("insert".equals(action)) {
			request.setAttribute("jobList", dao.selectJobList());
			request.setAttribute("deptList", dao.selectDeptList());
			request.setAttribute("manList", dao.selectManagerList());
			url ="/EmpInsert.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
