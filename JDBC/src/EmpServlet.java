

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.web.dao.EmpDAO;
import lab.web.vo.DeptVO;
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
			url = "/hr/EmpView.jsp";
		}else if("list".equals(action)) {
			request.setAttribute("list", dao.selectEmployeeList());
			url = "/hr/EmpList.jsp";
		}else if("insert".equals(action)) {
			request.setAttribute("jobList", dao.selectJobList());
			request.setAttribute("deptList", dao.selectDeptList());
			request.setAttribute("manList", dao.selectManagerList());
			request.setAttribute("message", "입력");
			request.setAttribute("action", action);
			url ="/hr/EmpInsert.jsp";
		} else if("depSearch".equals(action)) {
			int depId = Integer.parseInt(request.getParameter("depId"));
			request.setAttribute("list", dao.selectEmployeeByDepartment(depId));
			url = "/hr/EmpList.jsp";
		} else if("insertDep".equals(action)) {
			request.setAttribute("manList", dao.selectManagerList());
			request.setAttribute("cityList", dao.selectLocationList());
			url = "/hr/DepInsert.jsp";
		} else if("depList".equals(action)) {
			request.setAttribute("deptList", dao.selectDeptList());
			url = "/hr/DepList.jsp";
		} else if("update".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			EmpVO emp = dao.selectEmployee(empId);
			request.setAttribute("emp", emp);
			request.setAttribute("jobList", dao.selectJobList());
			request.setAttribute("deptList", dao.selectDeptList());
			request.setAttribute("manList", dao.selectManagerList());
			request.setAttribute("message", "수정");
			request.setAttribute("action", action);
			url ="/hr/EmpInsert.jsp";
		} else if("delete".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			EmpVO emp = dao.selectEmployee(empId);
			request.setAttribute("emp", emp);
			request.setAttribute("action", action);
			url = "/hr/Delete.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String url="";
		if("insert".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String sDate = request.getParameter("hireDate");
			SimpleDateFormat tool = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date hireDate = null;
			try {
				hireDate = new java.sql.Date(tool.parse(sDate).getTime());
			} catch(ParseException e) {
				e.printStackTrace();
			}
			String jobId = request.getParameter("jobId");
			Double salary = Double.parseDouble(request.getParameter("salary"));
			Double commissionPct = Double.parseDouble(request.getParameter("commissionPct"));
			int managerId = Integer.parseInt(request.getParameter("managerId"));
			int departmentId = Integer.parseInt(request.getParameter("departmentId"));

			EmpVO emp = new EmpVO();
			emp.setEmployeeId(empId);
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			emp.setEmail(email);
			emp.setPhoneNumber(phoneNumber);
			emp.setHireDate(hireDate);
			emp.setJobId(jobId);
			emp.setSalary(salary);
			emp.setCommissionPct(commissionPct);
			emp.setManagerId(managerId);
			emp.setDepartmentId(departmentId);
			dao.insertEmployee(emp);
			url = "/JDBC/Emp.do?action=list";
		} else if("insertDep".equals(action)) {
			int depId = Integer.parseInt(request.getParameter("depId"));
			String depName = request.getParameter("departmentName");
			int managerId = Integer.parseInt(request.getParameter("managerId"));
			int locationId = Integer.parseInt(request.getParameter("locationId"));
			
			DeptVO dep = new DeptVO();
			dep.setDepartmentId(depId);
			dep.setDepartmentName(depName);
			dep.setManagerId(managerId);
			dep.setLocationId(locationId);
			dao.insertDepartment(dep);
			url="/JDBC/Emp.do?action=depList";
		} else if("update".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String sDate = request.getParameter("hireDate");
			SimpleDateFormat tool = new SimpleDateFormat("yyyy-MM-dd");
			java.sql.Date hireDate = null;
			try {
				hireDate = new java.sql.Date(tool.parse(sDate).getTime());
			} catch(ParseException e) {
				e.printStackTrace();
			}
			String jobId = request.getParameter("jobId");
			Double salary = Double.parseDouble(request.getParameter("salary"));
			Double commissionPct = Double.parseDouble(request.getParameter("commissionPct"));
			int managerId = Integer.parseInt(request.getParameter("managerId"));
			int departmentId = Integer.parseInt(request.getParameter("departmentId"));

			EmpVO emp = new EmpVO();
			emp.setEmployeeId(empId);
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			emp.setEmail(email);
			emp.setPhoneNumber(phoneNumber);
			emp.setHireDate(hireDate);
			emp.setJobId(jobId);
			emp.setSalary(salary);
			emp.setCommissionPct(commissionPct);
			emp.setManagerId(managerId);
			emp.setDepartmentId(departmentId);
			dao.updateEmployee(emp);
			url="/JDBC/Emp.do?action=search&empId="+empId;
		} else if("delete".equals(action)) {
			int empId = Integer.parseInt(request.getParameter("empId"));
			dao.deleteEmployee(empId);
			url="/JDBC/Emp.do?action=list";
		}

		response.sendRedirect(url);
	}

}
