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
		EmpDetailVO emp = new EmpDetailVO();
		try {
			con = getConnection();
			String sql = "select * from employees emp "
					+ "join jobs j "
					+ "on emp.job_id=j.job_id "
					+ "join departments dep "
					+ "on emp.department_id=dep.department_id "
					+ "left join (select employee_id as manager_id, first_name||' '||last_name as manager_name "
					+ "from employees where employee_id in (select distinct manager_id from employees)) man "
					+ "on emp.manager_id=man.manager_id "
					+ "where employee_id = ?";
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
				emp.setJobTitle(rs.getString("job_title"));
				emp.setDepartmentName(rs.getString("department_name"));
				emp.setManagerName(rs.getString("manager_name"));
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
			String sql = "select * from departments";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				DeptVO dept = new DeptVO();
				dept.setDepartmentId(rs.getInt("department_id"));
				dept.setDepartmentName(rs.getString("department_name"));
				dept.setManagerId(rs.getInt("manager_id"));
				dept.setLocationId(rs.getInt("location_id"));
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
	public ArrayList<EmpVO> selectEmployeeByDepartment(int depId) {
		Connection con = null;
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		try {
			con = getConnection();
			String sql = "select * from employees where department_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, depId);
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
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("selectEmployeeByDepartment() 에러 발생 - 콘솔 확인");
		} finally {
			closeConnection(con);
		}
		return list;
	}

	public void insertEmployee(EmpVO emp) {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "insert into employees values(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, emp.getEmployeeId());
			stmt.setString(2, emp.getFirstName());
			stmt.setString(3, emp.getLastName());
			stmt.setString(4, emp.getEmail());
			stmt.setString(5, emp.getPhoneNumber());
			stmt.setDate(6, emp.getHireDate());
			stmt.setString(7, emp.getJobId());
			stmt.setDouble(8, emp.getSalary());
			stmt.setDouble(9, emp.getCommissionPct());
			stmt.setInt(10, emp.getManagerId());
			stmt.setInt(11, emp.getDepartmentId());
			if(stmt.executeUpdate() == 0 ) {
				throw new RuntimeException("입력되지 않았습니다.");
			}
		}catch(SQLException e) {
			if(e.getMessage().contains("unique")) {
				throw new RuntimeException("사원번호 또는 이메일 중복");
			}else {
				e.printStackTrace();
				throw new RuntimeException("insertEmployee() 에러 발생 ");
			}
		} finally {
			closeConnection(con);
		}
	}
	
	public ArrayList<LocationsVO> selectLocationList(){
		Connection con = null;
		ArrayList<LocationsVO> cityList = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select location_id, city from locations";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				LocationsVO city = new LocationsVO();
				city.setLocationId(rs.getInt("location_id"));
				city.setCity(rs.getString("city"));
				cityList.add(city);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("selectLocationList() 에러 발생 - 콘솔 확인");
		} finally {
			closeConnection(con);
		}
		return cityList;
	}
	
	public void insertDepartment(DeptVO dep) {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "insert into departments values(?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, dep.getDepartmentId());
			stmt.setString(2, dep.getDepartmentName());
			stmt.setInt(3, dep.getManagerId());
			stmt.setInt(4, dep.getLocationId());
			if(stmt.executeUpdate()==0) {
				throw new RuntimeException("입력되지 않았습니다.");
			}
		}catch(SQLException e) {
			if(e.getMessage().contains("UK")) {
				throw new RuntimeException("부서번호가 중복되었습니다.");
			}else {
				e.printStackTrace();
				throw new RuntimeException("insertDepartment() 에러 발생 ");
			}
		} finally {
			closeConnection(con);
		}
	}
}
