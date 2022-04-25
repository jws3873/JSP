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

public class ProductDAO {
	Connection con = null;                      // DB 연결하는 객체.
	PreparedStatement pstmt = null;             // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;                        // SQL문을 실행 후 결과 값을 가지고 있는 객체.

	String sql = null;                          // SQL문을 저장할 객체.

	// ProductDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//       기본 생성자의 접근 제어자를 public에서 private으로 바꿔 주어야 한다.

	// 2단계 : ProductDAO 객체를 정적 멤버로 선언해 주어야 한다. - static으로 선언해야 함.
	private static ProductDAO instance;

	private ProductDAO() {  }   // 기본 생성자


	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 함.
	public static ProductDAO getInstance() {

		if(instance == null) {
			instance = new ProductDAO();
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
	
	public int insertProduct(ProductDTO dto) {
		int result=0,count = 0;
		
		try {
			openConn();
			
			sql = "select max(pnum) from shop_products";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			sql = "insert into shop_products "
					+ "values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			
			pstmt.setString(2, dto.getPname());
			
			pstmt.setString(3, dto.getPcategory_fk());
			
			pstmt.setString(4, dto.getPcompany());
			
			pstmt.setString(5, dto.getPimage());
			
			pstmt.setInt(6, dto.getPqty());
			
			pstmt.setInt(7, dto.getPrice());
			
			pstmt.setString(8, dto.getPspec());
			
			pstmt.setString(9, dto.getPcontents());
			
			pstmt.setInt(10, dto.getPoint());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}// insertProduct() 메서드 end
	
	public List<ProductDTO> getProductList(){
		
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		try {
			
			openConn();
			
			sql = "select *from shop_products order by pnum desc";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setPnum(rs.getInt("pnum"));
				dto.setPname(rs.getString("pname"));
				dto.setPcategory_fk(rs.getString("pcategory_fk"));
				dto.setPcompany(rs.getString("pcompany"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setPspec(rs.getString("pspec"));
				dto.setPcontents(rs.getString("pcontents"));
				dto.setPoint(rs.getInt("point"));
				dto.setPinputdate(rs.getString("pinputdate"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return list;
		
	}// getProductList() 메서드 end
	
	
	// 제품번호에 해당하는 제품에 대한 상세정보를 조회하는 메서드
	public ProductDTO getproductContent(int no) {
		ProductDTO dto = new ProductDTO();
		
		try {
			openConn();
			
			sql = "select *from shop_products where pnum = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setPnum(rs.getInt("pnum"));
				dto.setPname(rs.getString("pname"));
				dto.setPcategory_fk(rs.getString("pcategory_fk"));
				dto.setPcompany(rs.getString("pcompany"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setPspec(rs.getString("pspec"));
				dto.setPcontents(rs.getString("pcontents"));
				dto.setPoint(rs.getInt("point"));
				dto.setPinputdate(rs.getString("pinputdate"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}// getproductContent()메서드 end
	
	//제품번호에 해당하는 제품의 정보를 수정하는 메서드.
	public int updateProduct(ProductDTO dto) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "update shop_products set pimage=?, "
					+ "pqty = ?, price = ?, pspec = ?, "
					+ "pcontents = ?, point = ? "
					+ "where pnum = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getPimage());
			pstmt.setInt(2, dto.getPqty());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setString(4, dto.getPspec());
			pstmt.setString(5, dto.getPcontents());
			pstmt.setInt(6, dto.getPoint());
			pstmt.setInt(7, dto.getPnum());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // updateProduct() 메서드 end
	
	
	// 제품번호에 해당하는 제품을 DB에서 삭제하는 메서드
	public int deleteProduct(int no) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "delete from shop_products where pnum = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
			sql = "update shop_products set pnum = pnum-1 "
					+ "where pnum > ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}// deleteProduct() 메서드 end
	
	
	// 카테고리 코드에 해당하는 제품의 전체 리스트를 조회하는 메서드.
	public List<ProductDTO> getProductList(String code){
		
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		try {
			
			openConn();
			
			sql = "select *from shop_products "
					+ "where pcategory_fk = ? ";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, code);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setPnum(rs.getInt("pnum"));
				dto.setPname(rs.getString("pname"));
				dto.setPcategory_fk(rs.getString("pcategory_fk"));
				dto.setPcompany(rs.getString("pcompany"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPqty(rs.getInt("pqty"));
				dto.setPrice(rs.getInt("price"));
				dto.setPspec(rs.getString("pspec"));
				dto.setPcontents(rs.getString("pcontents"));
				dto.setPoint(rs.getInt("point"));
				dto.setPinputdate(rs.getString("pinputdate"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return list;
		
	}// getProductList() 메서드 end
}
