package com.khie.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 *  DAO(Data Access Object) : 데이터접근 객체 ==> DB에 접속(연동) 하는 객체.
 *  DAO란 데이터베이스에 접속해서 데이터 추가, 수정, 삭제, 조회 등의 작업을 하는 클래스.
 *  - 일반적으로 JSP 또는 Servlet에서 위의 작업들을 같이 사용할 수 있지만,
 *  유지보수, 코드의 모듈화를 위해서 DAO 클래스를 따로 만들어서 사용을 함.
 *  
 *  MVC개념 알아보기
 */

public class DeptDAO {
	/*
	 * * 오라클 DB를 Java에 연결하는 방법 1. 물리적인 드라이버 설치 - JDBC 라이브러리를 이용한 DB에 연결하기 위해서는 해당 DB
	 * 프로그램의 기능을 수행하는 외부 라이브러리 파일을 프로젝트에 추가해 주어야 DB를 연결할 수 있음.
	 * //WebContent-WEB-INF-lib 폴더 경로에 jdbc 라이브러리를 추가해 주어야 한다.(파일 경로 = C:\app\dnjse\product\18.0.0\dbhomeXE\jdbc\lib)
	 */
	
	Connection con = null; // DB와 연동하는 객체
	PreparedStatement pstmt = null; // DB에 SQL문을 전송하는 객체
	ResultSet rs = null; // SQL문을 실행한 후 결고 ㅏ값을 가지고 있는 객체
	String sql="";
	
	public DeptDAO() { //기본 생성자
		
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
		
	} // 기본 생성자 end
	
	// selectList() 라는 메서드를 만들자.
	// DEPT 테이블이서 부서 목록 전체 리스트를 조회하여 해당 리스트를 반환해 주는 메서드.
	public List<DeptDTO> selectList() { //java.utill 패키지
		List<DeptDTO> list = new ArrayList<DeptDTO>(); // 다형성
		
		try {
			// 3단계 : 데이터 베이스에 SQL문을 전송하기 위한 쿼리문 작성
			sql = "select *from dept order by deptno";
			
			// 4단계 : SQL문을 데이터베이스 전송 객체에 저장
			pstmt = con.prepareStatement(sql);
			
			// 5단계 : SQL문을 데이터 베이스에 전송 및 SQL문 실행
			rs = pstmt.executeQuery(sql);
			
			
			while(rs.next()) {
				DeptDTO dto = new DeptDTO();
				// int deptno = rs.getInt("deptno");
				// String dname = rs.getString("dname");
				// String loc = rs.getString("loc");
				
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
				
				System.out.println("dto >>> "+dto);// 참조변수 주소
				list.add(dto);
				
			}
			
			// 6단계 : open 된 자원 종료시키기
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
		
		
	}// selectlist() 메서드 end
	
	
	// 부서 테이블에 부서를 추가하는 메서드.
	public int insertDept(DeptDTO dto) {
		int result = 0;

		try {
			// 1. 데이터베이스에 SQL문을 전송하기 위한 쿼리문 작성.
			sql = "insert into dept values(?,?,?)";
			
			// 2. SQL문을 데이터베이스 전송 객체에 저장.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getDname());
			pstmt.setString(3, dto.getLoc());
			
			// 3. SQL문을 데이터베이스에 전송 및 실행
			result = pstmt.executeUpdate();
			
			// 4. OPEN 되어 있는 자원 정료하기
			pstmt.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}// insertDept() 메서드 end
	
	
	// 매개변수로 넘어온 부서번호를 DB에서 삭제하는 메서드.
	public int deleteDept(int deptno) {
		int result = 0;
		
		
		try {
			// 1. SQL문을 데이터베이스에 전송할 쿼리문을 작성.
			sql = "delete from dept where deptno = ?";
			
			
			// 2. SQL문을 데이터베이스 전송 객체에 저장
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, deptno);
			
			//3. SQL문을 데이터베이스에 전송 및 실행
			result = pstmt.executeUpdate();
			
			//4. open 자원 종료시키기
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}// deleteDept()메서드 end



}
