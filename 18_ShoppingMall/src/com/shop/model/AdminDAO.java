package com.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class AdminDAO {
	Connection con = null;                      // DB 연결하는 객체.
	PreparedStatement pstmt = null;             // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;                        // SQL문을 실행 후 결과 값을 가지고 있는 객체.

	String sql = null;                          // SQL문을 저장할 객체.

	// AdminDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//       기본 생성자의 접근 제어자를 public에서 private으로 바꿔 주어야 한다.

	// 2단계 : AdminDAO 객체를 정적 멤버로 선언해 주어야 한다. - static으로 선언해야 함.
	private static AdminDAO instance;

	private AdminDAO() {  }   // 기본 생성자


	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 함.
	public static AdminDAO getInstance() {

		if(instance == null) {
			instance = new AdminDAO();
		}
		return instance;

	}
	
	public void openConn() {
		try {
			Context ctx = new InitialContext();
			
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			con = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//DB에 연결된 자원을 종료하는 메서드
	
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
		
		
		try {
			if(rs !=null) {
			rs.close(); 
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}//closeConn() 메서드 end
	
	// 관리자 로그인 화면에서 입력한 아이디와 비밀번호로 관리자인지 확인하는 메서드.
	
	public int adminCheck(String id,String pwd) {
		int result = 0; // 관리자 아이디가 아닌 경우 - 존재하지 않는 관리자인 경우.
		
		try {
			openConn();
			
			sql = "select *from admin_shop where admin_id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// DB상에 관리자 로그인 페이지에서 입력한 아이디가 존재하는 경우
				if(pwd.equals(rs.getString("admin_pwd"))) {
					// 관리자인 경우 (아이디와 비밀번호가 일치하는 관리자)
					result = 1;
				}else {
					// 비밀번호가 틀린 경우(아이디는 일치하나 비밀번호가 틀린 경우)
					result = -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
		
	}// adminCheck() 메서드 end
	
	// 관리자에 대한 상세정보를 조회하는 메서드
	public AdminDTO getAdmin(String id) {
		AdminDTO dto = new AdminDTO();
		
		try {
			openConn();
			
			sql = "select *from admin_shop where admin_id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setAdmin_id(rs.getString("admin_id"));
				dto.setAdmin_pwd(rs.getString("admin_pwd"));
				dto.setAdmin_name(rs.getString("admin_name"));
				dto.setAdmin_email(rs.getString("admin_email"));
				dto.setAdmin_date(rs.getString("admin_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
		
	}// getAdmin() 메서드 end
	
	
	
}
