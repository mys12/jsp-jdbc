

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmpServlet
 */
@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpServlet() {
        super();
    }

    static {
    	try {
    		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
    		System.out.println("드라이버 로드 완료");
    	} catch(SQLException e) {
    		System.out.println("드라이버 로드 실패");
    	}
    	
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url ="jdbc:oracle:thin:@127.0.0.1:1521/xepdb1";
		String id = "hr";
		String pw = "hr";
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,id,pw);
			String sql = "select * from employees where employee_id=100";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				System.out.printf("사원번호 : %d\n", rs.getInt("employee_id"));
				System.out.printf("이름 : %s\n", rs.getString("first_name"));
				System.out.printf("성 : %s\n", rs.getString("last_name"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {try { con.close();}catch(SQLException e) {}}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
