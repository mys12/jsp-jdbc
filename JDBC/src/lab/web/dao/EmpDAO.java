package lab.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lab.web.vo.*;

public class EmpDAO {

	static {
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("드라이버 로드 완료");
		} catch (SQLException e) {
			System.out.println("드라이버 로드 실패");
		}
	}
	
	private Connection getConnection() {
		Connection con = null;
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
			con = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void closeConnection(Connection con) {
		if(con!=null) try {con.close();} catch(SQLException e) {}
	}
	
	public EmpVO selectEmployee(int empId) {
		Connection con = null;
		EmpVO emp = new EmpVO();
		try {
			con = getConnection();
			String sql = "select * from employees where employee_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, empId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				emp.setEmployeeId(rs.getInt(1));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble(9));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt(11));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("selectEmployee() 에러 발생 - 콘솔 확인");
		} finally {
			closeConnection(con);
		}
		return emp;
	}
	
	public ArrayList<EmpVO> selectEmployeeList(){
		Connection con = null;
		ArrayList<EmpVO> list = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from employees";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt(1));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble(9));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt(11));
				list.add(emp);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("selectEmployeeList() 에러 발생 - 콘솔 확인");
		} finally {
			closeConnection(con);
		}
		return list;
	}
	
	public ArrayList<JobVO> selectJobList() {
		Connection con = null;
		ArrayList<JobVO> jobList = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select job_id, job_title from jobs";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				JobVO job = new JobVO();
				job.setJobId(rs.getString("job_id"));
				job.setJobTitle(rs.getString("job_title"));
				jobList.add(job);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("selectJobList() 에러 발생 - 콘솔 확인");
		} finally {
			closeConnection(con);
		}
		return jobList;
	}
	
	public ArrayList<DeptVO> selectDeptList() {
		Connection con = null;
		ArrayList<DeptVO> deptList = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select department_id, department_name from departments";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				deptList.add(dept);
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("selectDeptList() 에러 발생 - 콘솔 확인");
		} finally {
			closeConnection(con);
		}
		return deptList;
	}
	
	public ArrayList<EmpVO> selectManagerList() {
		Connection con = null;
		ArrayList<EmpVO> manList = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select employee_id, first_name||' '||last_name as manager_name from employees where employee_id in (select distinct manager_id from employees)";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				EmpVO man = new EmpVO();
				man.setEmployeeId(rs.getInt("employee_id"));
				man.setFirstName(rs.getString("manager_name"));
				manList.add(man);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("selectManagerList() 에러 발생 - 콘솔 확인");
		} finally {
			closeConnection(con);
		}
		return manList;
	}
}
