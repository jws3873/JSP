package com.upload.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UploadDAO {
	Connection con = null;                      // DB 연결하는 객체.
	PreparedStatement pstmt = null;             // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;                        // SQL문을 실행 후 결과 값을 가지고 있는 객체.

	String sql = null;                          // SQL문을 저장할 객체.

	// UploadDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//       기본 생성자의 접근 제어자를 public에서 private으로 바꿔 주어야 한다.

	// 2단계 : UploadDAO 객체를 정적 멤버로 선언해 주어야 한다. - static으로 선언해야 함.
	private static UploadDAO instance;

	private UploadDAO() {  }   // 기본 생성자


	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 함.
	public static UploadDAO getInstance() {

		if(instance == null) {
			instance = new UploadDAO();
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
	
	
	// upload 테이블에서 전체 리스트를 조회하는 메서드
	public List<UploadDTO> getUploadList(){
		
		List<UploadDTO> list = new ArrayList<UploadDTO>();
		
		try {
			openConn();
			
			sql = "select *from upload order by upload_no desc";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UploadDTO dto = new UploadDTO();
				
				dto.setUpload_no(rs.getInt("upload_no"));
				dto.setUpload_writer(rs.getString("upload_writer"));
				dto.setUpload_title(rs.getString("upload_title"));
				dto.setUpload_cont(rs.getString("upload_cont"));
				dto.setUpload_pwd(rs.getString("upload_pwd"));
				dto.setUpload_file(rs.getString("upload_file"));
				dto.setUpload_hit(rs.getInt("upload_hit"));
				dto.setUpload_date(rs.getString("upload_date"));
				dto.setUpload_update(rs.getString("upload_update"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}// getUploadList() 메서드 end
	
	public int insertUpload(UploadDTO dto) {
		
		int result = 0,count = 0;
		
		try {
			openConn();
			
			sql = "select max(upload_no) from upload";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			sql = "insert into upload "
					+ "values(?,?,?,?,?,?,default,sysdate,'')";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			
			pstmt.setString(2, dto.getUpload_writer());
			
			pstmt.setString(3, dto.getUpload_title());
			
			pstmt.setString(4, dto.getUpload_cont());
			
			pstmt.setString(5, dto.getUpload_pwd());
			
			pstmt.setString(6, dto.getUpload_file());
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
		
	} // insertUpload() 메서드 end
	
	public void uploadHit(int no) {

		try {
			openConn();
			
			sql = "update upload set upload_hit = upload_hit+1 "
					+ "where upload_no = ?"; 

			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
	}// uploadHit() 메서드 end
	
	
	// upload 테이블의 글 번호에 해당하는 게시글의 상세내역을 조회하는 메서드.
	public UploadDTO uploadContent(int no) {
		UploadDTO dto = new UploadDTO();
		
		try {
			openConn();
			
			sql = "select *from upload where upload_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setUpload_no(rs.getInt("upload_no"));
				dto.setUpload_writer(rs.getString("upload_writer"));
				dto.setUpload_title(rs.getString("upload_title"));
				dto.setUpload_cont(rs.getString("upload_cont"));
				dto.setUpload_pwd(rs.getString("upload_pwd"));
				dto.setUpload_file(rs.getString("upload_file"));
				dto.setUpload_hit(rs.getInt("upload_hit"));
				dto.setUpload_date(rs.getString("upload_date"));
				dto.setUpload_update(rs.getString("upload_update"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
		
	}// uploadContent()메서드 end
	
	
	// upload 테이블에 게시글 번호에 해당하는 게시글을 수정하는 메서드
	public int updateUpload(UploadDTO dto) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "select *from upload "
					+ "where upload_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getUpload_no());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(dto.getUpload_pwd().equals(rs.getString("upload_pwd"))) {
					if(dto.getUpload_file() == null) {
						sql = "update upload set upload_title = ?, upload_cont = ?, "
								+ "upload_update = sysdate where upload_no = ?";
						
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getUpload_title());
						pstmt.setString(2, dto.getUpload_cont());
						pstmt.setInt(3, dto.getUpload_no());
					}else { // 첨부파일이 있는 경우
						sql = "update upload set upload_title = ?, upload_cont = ?, "
								+ "upload_file = ?, "
								+ "upload_update = sysdate where upload_no = ?";
						
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, dto.getUpload_title());
						pstmt.setString(2, dto.getUpload_cont());
						pstmt.setString(3, dto.getUpload_file());
						pstmt.setInt(4, dto.getUpload_no());
					}
					
					result = pstmt.executeUpdate();
				}else { // 비밀번호가 틀린 경우
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
		
	} // updateUpload() 메서드 end
	
	
	public int deleteUpload(int no,String pwd) {
		
		int result = 0;
		
		try {
			openConn();

			sql = "delete from upload where upload_no = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, no);

			result = pstmt.executeUpdate();

			sql = "update upload set upload_no = upload_no-1 "
					+ "where upload_no > ?";
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
		
	} //deleteUpload() 메서드 end
	
	
}
