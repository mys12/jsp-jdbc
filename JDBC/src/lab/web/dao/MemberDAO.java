package lab.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import lab.web.vo.MemberVO;

public class MemberDAO {

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
	
	public void insertMember(MemberVO member) {
		Connection con = null;
		try {
			con = getConnection();
			String sql = "insert into member values(?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, member.getUserId());
			stmt.setString(2, member.getName());
			stmt.setString(3, member.getPassword());
			stmt.setString(4, member.getEmail());
			stmt.setString(5, member.getAddress());
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("insertMember() 에러 발생 - 콘솔 확인");
		} finally {
			closeConnection(con);
		}
	}
	public String getPassword(String userId) {
		Connection con = null;
		String pw = null;
		try {
			con = getConnection();
			String sql = "select password from member where userid=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, userId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				pw = rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("getPassword() 에러 발생 - 콘솔 확인");
		}finally {
			closeConnection(con);
		}
		return pw;
	}
}
