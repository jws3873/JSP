package com.shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CategoryDAO {
	Connection con = null;                      // DB 연결하는 객체.
	PreparedStatement pstmt = null;             // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;                        // SQL문을 실행 후 결과 값을 가지고 있는 객체.

	String sql = null;                          // SQL문을 저장할 객체.

	// CategoryDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//       기본 생성자의 접근 제어자를 public에서 private으로 바꿔 주어야 한다.

	// 2단계 : CategoryDAO 객체를 정적 멤버로 선언해 주어야 한다. - static으로 선언해야 함.
	private static CategoryDAO instance;

	private CategoryDAO() {  }   // 기본 생성자


	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 함.
	public static CategoryDAO getInstance() {

		if(instance == null) {
			instance = new CategoryDAO();
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
	
	public int insertCategory(String code,String name) {
		int result = 0,count = 0;
		
		try {
			openConn();
			
			sql = "select max(category_num) from shop_category";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			sql = "insert into shop_category values(?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, code);
			pstmt.setString(3, name);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
		
	}// insertCategory() end
	
	public List<CategoryDTO> getCartList(){
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		
		try {
			openConn();
			
			sql = "select * from shop_category order by category_num desc";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setCategory_num(rs.getInt("category_num"));
				dto.setCategory_code(rs.getString("category_code"));
				dto.setCategory_name(rs.getString("category_name"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
		
	}// getCartList() 메서드 end
	
	public int deleteCategory(int num) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "delete from shop_category where category_num = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
			
			sql = "update shop_category set category_num = category_num-1 "
					+ "where category_num>?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
		
	}// deleteCategory() 메서드 end
	
	
	
	
	
	
	
	
}
