package com.board2.model;

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

import com.board2.model.BoardDTO;




public class BoardDAO {
	Connection con = null;                      // DB 연결하는 객체.
	PreparedStatement pstmt = null;             // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;                        // SQL문을 실행 후 결과 값을 가지고 있는 객체.

	String sql = null;                          // SQL문을 저장할 객체.

	// BoardDAO 객체를 싱글턴 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//       기본 생성자의 접근 제어자를 public에서 private으로 바꿔 주어야 한다.

	// 2단계 : BoardDAO 객체를 정적 멤버로 선언해 주어야 한다. - static으로 선언해야 함.
	private static BoardDAO instance;

	private BoardDAO() {  }   // 기본 생성자


	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는 메서드를
	// 만들어서 이 getInstance() 메서드에 외부에서 접근할 수 있게 해야 함.
	public static BoardDAO getInstance() {

		if(instance == null) {
			instance = new BoardDAO();
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
	
	public void closeConn(ResultSet rs,PreparedStatement pstmt, Connection con) {
		try {
			if(rs !=null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con !=null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getBoardCount() {
		int count = 0;
		
		try {
			openConn();
			
			sql = "select count(*)from board ";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return count;
	}// getBoardCount() 메서드 end
	
	public List<BoardDTO> getBoardList(int page,int rowsize){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		int startNo = (page*rowsize)-(rowsize-1);
		int endNo = (page*rowsize);
		
		try {
			openConn();
			
			sql = "select *from (select row_number() over(order by board_no desc) rnum, b.*from board b) "
					+ "where rnum >= ? and rnum <= ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startNo);
			pstmt.setInt(2, endNo);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				list.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
	}// getBoardList() 메서드 end
	
	public int insertBoard(BoardDTO dto) {
		int result =0,count = 0 ;
		
		try {
			openConn();
			
			sql = "select max(board_no)from board";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			sql = "insert into board values(?,?,?,?,?,default,sysdate,'')";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getBoard_writer());
			pstmt.setString(3, dto.getBoard_title());
			pstmt.setString(4, dto.getBoard_cont());
			pstmt.setString(5, dto.getBoard_pwd());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	} // insertBoard()메서드 end
	
	public void boardHit(int no) {
		
		try {
			openConn();
			
			sql = "update board set board_hit = board_hit+1 where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
	}// boardHit() 메서드 end
	
	public BoardDTO getBoardCont(int no) {
		BoardDTO dto = new BoardDTO();
		
		try {
			openConn();
			
			sql = "select *from board where board_no = ?";
			
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
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}// getBoardCont() 메서드 end
	
	public int insertUpdate(BoardDTO dto) {
		int result = 0;
		try {
			
			openConn();
			
			sql = "update board set board_title = ?,board_cont = ?, board_update = sysdate"
					+ " where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_cont());
			pstmt.setInt(3, dto.getBoard_no());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}// insertUpdate() 메서드 end
	
	public int deleteBoard(int no,String pwd) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "select *from board where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("board_pwd").equals(pwd)) {
					
					sql = "delete from board where board_no = ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, no);
					
					result = pstmt.executeUpdate();
					
					sql = "update board set board_no = board_no-1 where board_no > ?";
					
					pstmt = con.prepareStatement(sql);

					pstmt.setInt(1, no);
					
					pstmt.executeUpdate();
					
				}else {
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
		
	}// deleteBoard() 메서드 end
	
	public int getsearchCount(String field,String keyward) {
		int count = 0;
		try {
			openConn();
			if(field.equals("title")) {
				sql = "select count(*) from board where board_title like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyward+"%");
			}else if (field.equals("content")) {
				sql = "select count(*) from board where board_cont like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyward+"%");
			}else if (field.equals("title_content")) {
				sql = "select count(*) from board "
						+ "where board_title like ? or board_cont = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyward+"%");
				pstmt.setString(2, "%"+keyward+"%");
			}else {
				sql = "select count(*) from board where board_writer like ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyward+"%");
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return count;
		
	} // getsearchCount() 메서드 end
	
	public List<BoardDTO> getSearchList(String field,String keyward,int page,int rowsize){
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		int startNo = (page*rowsize)-(rowsize-1);

		int endNo = (page*rowsize);
		
		try {
			openConn();
			
			if(field.equals("title")) {
				sql = "select *from (select row_number()over(order by board_no desc) rnum, b.*from board b where board_title like ?) " + 
						"where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyward+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
			}else if(field.equals("content")) {
				sql = "select *from (select row_number()over(order by board_no desc) rnum, b.*from board b where board_cont like ?) " + 
						"where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyward+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
			}else if(field.equals("title_content")) {
				sql = "select *from (select row_number()over(order by board_no desc) rnum, b.*from board b "
						+ "where board_title like ? or board_cont like ?) " + 
						"where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyward+"%");
				pstmt.setString(2, "%"+keyward+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
			}else {
				sql = "select *from (select row_number()over(order by board_no desc) rnum, b.*from board b where board_writer like ?) " + 
						"where rnum >= ? and rnum <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyward+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				list.add(dto);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConn(rs, pstmt, con);
		}
		
		return list;
		
	} // getSearchList() 메서드 end
	

}

