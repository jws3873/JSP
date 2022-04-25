package com.reply.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BbsDAO {

	Connection con = null;                      // DB 연결하는 객체.
	PreparedStatement pstmt = null;             // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;                        // SQL문을 실행 후 결과 값을 가지고 있는 객체.

	String sql = null;                          // SQL문을 저장할 객체.

	// BbsDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//       기본 생성자의 접근 제어자를 public에서 private으로 바꿔 주어야 한다.

	// 2단계 : BbsDAO 객체를 정적 멤버로 선언해 주어야 한다. - static으로 선언해야 함.
	private static BbsDAO instance;

	private BbsDAO() {  }   // 기본 생성자


	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 함.
	public static BbsDAO getInstance() {

		if(instance == null) {
			instance = new BbsDAO();
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
	
	public List<BbsDTO> getBbsList(){
		
		List<BbsDTO> list = new ArrayList<BbsDTO>();
		
		try {
			openConn();
			
			
			sql = "select *from jsp_bbs order by board_group desc,board_step";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BbsDTO dto = new BbsDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs,pstmt,con);
		}
		return list;
		
	}
	
	//jsp_bbs 테이블에 게시글을 추가하는 메서드.
	public int insertBoard(BbsDTO dto) {
		int result = 0,count=0;
		
		try {
			openConn();
			
			sql = "select max(board_no) from jsp_bbs";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			sql = "insert into jsp_bbs values(?, ?, ?, ?, ?, default, sysdate, '',?,0,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			pstmt.setInt(6, count);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
		
	}// insertBoard()메서드 end
	
	// jsp_bbs 테이블의 게시물 번호에 대항하는 조회수를 증사시키는 메서드
	public void bbsHit(int no) {
		try {
			openConn();
			
			sql = "update jsp_bbs set board_hit = board_hit+1 where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		
	}// bbsHit() 메서드 end
	
	
	// jsp_bbs 테이블에서 게시글 번호에 해당하는 게시물의 상세내역을 조회하는 메서드
	public BbsDTO getBbsContent(int no) {
		BbsDTO dto = new BbsDTO();
		
		try {
			openConn();
			
			sql = "select *from jsp_bbs where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
		
	} // getBbsContent()메서드 end
	
	
	//jsp_bbs 테이블에 글 번호에 해당하는 게시글을 수정하는 메서드.
	public int updateBbs(BbsDTO dto) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "select *from jsp_bbs where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getBoard_no());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				if(rs.getString("board_pwd").equals(dto.getBoard_pwd())) {
					
					sql = "update jsp_bbs set board_title = ?, board_cont = ?,board_update = sysdate "
							+ "where board_no = ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, dto.getBoard_title());
					
					pstmt.setString(2, dto.getBoard_cont());
					
					pstmt.setInt(3, dto.getBoard_no());
					
					result = pstmt.executeUpdate();
				}else {
					// 비밀번호가 틀린 경우
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
		
	} // updateBbs() 메서드 end
	
	// jsp_bbs 테이블에서 게시글을 삭제하는 메서드
	public int deleteBbs(int no, String pwd) {
		
		int result = 0;
		
		try {
		openConn();
		
		sql = "select *from jsp_bbs where board_no = ?";
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, no);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			if (pwd.equals(rs.getString("board_pwd"))) {
				
				sql = "delete from jsp_bbs where board_no = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, no);
				
				result = pstmt.executeUpdate();
				
				sql = "update jsp_bbs set board_no = board_no - 1, "
						+ "board_group = board_group-1 "
						+ "where board_no > ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, no);
				
				pstmt.executeUpdate();
				
			}else {
				result = -1;
			}
		}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		} 
		
		return result;
		
		
	} // deleteBbs() 메서드 end
	

	public void updateBbs(int group,int step) {
		
		
		try {
			openConn();
			
			sql = "update jsp_bbs set board_step = board_step+1 "
					+ "where board_group = ? and board_step > ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, group);
			pstmt.setInt(2, step);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
	}// updateBbs() 메서드 end
	
	public int replyBbs(BbsDTO dto) {
		int result =0,count = 0;
		
		try {
			openConn();
			
			sql = "select max(board_no) from jsp_bbs";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			sql = "insert into jsp_bbs values(?,?,?,?,?,default,sysdate,'',?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			pstmt.setInt(6, dto.getBoard_group());
			pstmt.setInt(7, dto.getBoard_step()+1);
			pstmt.setInt(8, dto.getBoard_indent()+1);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}// replyBbs() 메서드 end
	
	
	
	
}
