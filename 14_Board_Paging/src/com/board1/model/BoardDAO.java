package com.board1.model;

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
	
	
	// board 테이블의 전체 게시물의 수를 확인하는 메서드.
	public int getBoardCount() {
		int count = 0;

		try {
			openConn();
			
			sql = "select count(*) from board";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}// getBoardCount() 메서드 end
	
	// board 테이블에서 현재 페이지에 해당하는 게시물을 조회하는 메서드
	public List<BoardDTO> getBoardList(int page,int rowsize){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		// 해당 페이지에서 시작 번호
		int startNo = (page*rowsize) - (rowsize-1);
		
		// 해당 페이지에서 끝 번호 
		int endNo = (page*rowsize);
		
		try {
			openConn();
			
			sql = "select *from "
					+ "(select row_number()"
					+ "over(order by board_no desc) rnum,"
					+ "b.*from board b)"
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
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}// getBoardList() 메서드 end
	
	public int insertBoard(BoardDTO dto) {
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "select max(board_no) from board";
			
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
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	//board 테이블의 게시물 번호에 해당하는 게시글의 조회수를 증가시키는 메서드.
	public void boardHit(int no) {
		
		try {
			openConn();
			
			sql = "update board set board_hit = board_hit+1 where board_no > ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // boardHit() 메서드 end
	
	// board 테이블에서 게시글 번호에 해당하는 게시글을 조회하는 메서드.
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
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	} // getBoardCont() 메서드 end
	
	public int boardupdate(BoardDTO dto) {
		int result = 0;
		
		try {
			openConn();
			
			sql = "update board set board_title = ?, board_cont = ?"
					+ "where board_no = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoard_title());
			pstmt.setString(2, dto.getBoard_cont());
			pstmt.setInt(3, dto.getBoard_no());
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}// boardupdate() end
	
	
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
					
					sql ="update board set board_no=board_no-1 where board_no >?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, no);
					
					pstmt.executeUpdate();
					
				}else {
					result = -1;
				}
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int searchListCount(String field,String keyword) {
		int count = 0;
		
		openConn();
		
		if(field.equals("title")) {
			try {
				sql = "select count(*)from board"
						+ " where board_title like ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+keyword+"%");
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count = rs.getInt(1);
				}
				
				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(field.equals("content")) {
			try {
				sql = "select count(*)from board"
						+ " where board_cont like ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+keyword+"%");
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count = rs.getInt(1);
				}
				
				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(field.equals("title_content")) {
			try {
				sql = "select count(*)from board"
						+ " where board_title like ? or board_cont like ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+keyword+"%");

				pstmt.setString(2, "%"+keyword+"%");
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count = rs.getInt(1);
				}
				
				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				sql = "select count(*)from board"
						+ " where board_writer like ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+keyword+"%");
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					count = rs.getInt(1);
				}
				
				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	public List<BoardDTO> searchBoardList(String field,String keyword,int page,int rowsize){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		// 해당 페이지에서 시작번호
		int startNo = (page*rowsize)-(rowsize-1);
		int endNo = (page*rowsize);
		
		openConn();
		
		if(field.equals("title")) {
			try {
				sql = "select *from "
						+ "(select row_number()"
						+ "over(order by board_no desc) rnum,"
						+ "b.*from board b where board_title like ?)"
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				
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

				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(field.equals("content")) {
			try {
				sql = "select *from "
						+ "(select row_number()"
						+ "over(order by board_no desc) rnum,"
						+ "b.*from board b where board_cont like ?)"
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				
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

				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(field.equals("title_content")) {
			try {
				sql = "select *from "
						+ "(select row_number()"
						+ "over(order by board_no desc) rnum,"
						+ "b.*from board b where board_title like ? or board_cont like ?)"
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");

				pstmt.setInt(3, startNo);
				pstmt.setInt(4, endNo);
				
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

				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				sql = "select *from "
						+ "(select row_number()"
						+ "over(order by board_no desc) rnum,"
						+ "b.*from board b where board_writer like ?)"
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
				
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

				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	
	
}

