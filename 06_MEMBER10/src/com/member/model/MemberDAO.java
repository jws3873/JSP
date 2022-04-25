package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	Connection con = null; // DB와 연동하는 객체
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체
	ResultSet rs = null; // SQL문을 실행한 후 결고 ㅏ값을 가지고 있는 객체
	String sql="";
	
	public MemberDAO() {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "web";
		String password = "1234";
		
		try {
			// 1단계 오라클 드라이버 로딩.
			Class.forName(driver);
			
			// 2단계 : 오라클 데이터 베이스와 연결을 진행
			con = DriverManager.getConnection(url, user, password);
			
			if(con != null) {
				System.out.println("데이터 베이스 연결 성공!!!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	
	} // 생성자 end
	
	// MEMBER10 테이블에서 회원 전체 목록을 조회하는 메서드.
	public List<MemberDTO> getMemberList(){
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			//1. DB에 전송할 SQL문 작성.
			sql = "select *from MEMBER10 order by num desc";
			
			//2. SQL문을 데이터 베이스 전송 객체에 저장
			pstmt = con.prepareStatement(sql);
			
			// 3. 데이터 베이스에 SQL문을 전송 및 실행
			rs = pstmt.executeQuery();
			
			// 4. SQL문 실행 결과 값을 DTO 객체에 저장하여 list에 추가
			while(rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getNString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));
				list.add(dto);
			}
			
			// 5. DB에 연결되어 있는 자원 종료작업.
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}//getMemberList() end
	
	public int insertMember(MemberDTO dto) {
		int result = 0,count= 0;
		
		try {
			sql = "select max(num) from member10";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			
			sql = "insert into member10 values(?,?,?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getMemid());
			pstmt.setString(3, dto.getMemname());
			pstmt.setString(4, dto.getPwd());
			pstmt.setInt(5, dto.getAge());
			pstmt.setInt(6, dto.getMileage());
			pstmt.setString(7, dto.getJob());
			pstmt.setString(8, dto.getAddr());
			
			result = pstmt.executeUpdate();
			
			// open 자원 종료시키기
			
			pstmt.close(); rs.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}// insetMember() 메서드 end
	
	
	// 회원 번호에 해당하는 회원의 정보를 조회하는 메서드.
	public MemberDTO getContentMember(int member_no) {
		MemberDTO dto = new MemberDTO();
		
		try {
			sql = "select *from member10 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getNString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));
			}
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	} //get ContentMember() 메서드 end
	
	
	// MEMBER10 테이블에서 회원번호에 해당하는 회원의 정보를 수정하는 메서드.
	public int updateMember(MemberDTO dto) {
		int result =0;
		
		//회원의 정보가 수정되려면 회원의 비밀번호가 일치하여야 한다.
		//데이터베이스와 수정폼상의 비밀번호가 일치해야지 수정이 가능.
		try {
			sql= "select *from member10 where num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("pwd").equals(dto.getPwd())) {
					sql = "update member10 set age=?,"
							+ "mileage=?,job=?,"
							+ "addr=? where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, dto.getAge());
					pstmt.setInt(2, dto.getMileage());
					pstmt.setString(3, dto.getJob());
					pstmt.setString(4, dto.getAddr());
					pstmt.setInt(5, dto.getNum());
					result = pstmt.executeUpdate();
				}else { // 비밀번호가 틀린 경우
					result =-1;
				}
			}
			
			rs.close(); pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} //updateMember() 메서드 end
	
	public int deleteMember(int member_num) {
		int result = 0;
		try {
			sql = "delete from member10 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			result = pstmt.executeUpdate();
			
			pstmt.close(); //con.close(); updateNum()메서드를 위해 닫지 말아야 한다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	} // deleteMember() 메서드 종료
	
	
	// 회원 번호 순번 다시 작업해 주는 메서드.
	public void updateNum(int member_num) {
		
		try {
			sql = "update member10 set num = num -1 where num> ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_num);
			pstmt.executeUpdate();
			
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}//update() 메서드 end

}
