package com.products.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ProductsDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs= null;
	String sql = null;
	private static ProductsDAO instance = null;
	
	private ProductsDAO() { }
	
	public static ProductsDAO getInstance() {
		if(instance == null) {
			instance = new ProductsDAO();
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
	
	public List<ProductsDTO> productsList(){
		List<ProductsDTO> list = new ArrayList<ProductsDTO>();
		
		
		
		try {
			openConn();
			
			sql = "select *from products order by pnum desc";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductsDTO dto = new ProductsDTO();
				dto.setPnum(rs.getInt("pnum"));
				dto.setCategory_fk(rs.getString("category_fk"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setEp_code_fk(rs.getString("ep_code_fk"));
				dto.setInput_price(rs.getInt("input_price"));
				dto.setOutput_price(rs.getInt("output_price"));
				dto.setTrans_cost(rs.getInt("trans_cost"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setCompany(rs.getString("company"));
				list.add(dto);
			}
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	} // productsList()메서드 end
	
	public List<CategoryDTO> categoryList(){
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		
		try {
			openConn();
			
			sql = "select *from category order by CNUM desc";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setCnum(rs.getInt("CNUM"));
				dto.setCategory_code(rs.getString("CATEGORY_CODE"));
				dto.setCategory_name(rs.getString("CATEGORY_NAME"));
				list.add(dto);
			}
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public int productInsert(ProductsDTO dto) {
		int result = 0,count = 0;
		
		try {
			
			openConn();
			
			sql = "select max(pnum) from products";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			sql = "insert into products values(?,?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setNString(2, dto.getCategory_fk());
			pstmt.setString(3, dto.getProduct_name());
			pstmt.setString(4, dto.getEp_code_fk());
			pstmt.setInt(5, dto.getInput_price());
			pstmt.setInt(6, dto.getOutput_price());
			pstmt.setInt(7, dto.getTrans_cost());
			pstmt.setInt(8, dto.getMileage());
			pstmt.setString(9, dto.getCompany());
			
			result = pstmt.executeUpdate();
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ProductsDTO productContent(int pnum) {
		
		ProductsDTO dto = new ProductsDTO();
		
		
		try {
			openConn();
			
			sql = "select *from products where pnum = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, pnum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setPnum(rs.getInt("pnum"));
				dto.setCategory_fk(rs.getString("category_fk"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setEp_code_fk(rs.getString("ep_code_fk"));
				dto.setInput_price(rs.getInt("input_price"));
				dto.setOutput_price(rs.getInt("output_price"));
				dto.setTrans_cost(rs.getInt("trans_cost"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setCompany(rs.getString("company"));
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
		
	}
	
	public int productUpdate(ProductsDTO dto) {
		int result =0;
		
		try {
			openConn();
			
			sql = "update products set input_price=?,output_price=?,trans_cost=?,mileage=? where pnum = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getInput_price());
			pstmt.setInt(2, dto.getOutput_price());
			pstmt.setInt(3, dto.getTrans_cost());
			pstmt.setInt(4, dto.getMileage());
			pstmt.setInt(5, dto.getPnum());
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public int productDelete(int pnum) {
		int result =0;
		
		try {
			openConn();
			
			sql = "delete from products where pnum=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, pnum);
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void sequenceUpdate(int pnum) {
		try {
			
			openConn();
			
			sql = "update products set pnum = pnum-1 where pnum > ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, pnum);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
