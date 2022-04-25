package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {

	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// EmpDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : ProductDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static EmpDAO instance = null;
	
	
	private EmpDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static EmpDAO getInstance() {
		
		if(instance == null) {
			instance = new EmpDAO();
		}
		return instance;
		
	}  // getInstance() 메서드 end
	
	
	// DB를 연동하는 작업을 진행하는 메서드
	public void openConn() {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		String user = "web";
		
		String password = "1234";
		
		
		try {
			// 1단계 : 오라클 드라이버 로딩
			Class.forName(driver);
			
			// 2단계 : 오라클 데이터베이스와 연결 작업 진행.
			con = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}  // openConn() 메서드 end
	
	
	// EMP 테이블에서 전체 사원 리스트를 조회하는 메서드.
	public List<EmpDTO> allList() {
		
		List<EmpDTO> list = new ArrayList<EmpDTO>();
		
		
		try {
			openConn();
			
			sql = "select * from emp order by empno desc";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpDTO dto = new EmpDTO();
				
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
				
				list.add(dto);
				
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}  // allList() 메서드 end
	
	
	// EMP 테이블에서 담당업무를 조회하는 메서드.
	public List<String> getJobList() {
		
		List<String> jobList = new ArrayList<String>();
		
		try {
			openConn();
			
			sql = "select distinct(job) from emp order by job";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String job = rs.getString("job");
				
				jobList.add(job);
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jobList;
	}  // getJobList() 메서드 end
	
	
	// EMP 테이블에서 담당업무가 "MANAGER" 인 사원을 조회하는 메서드.
	public List<EmpDTO> getMgrList() {
		
		List<EmpDTO> mgrList = new ArrayList<EmpDTO>();
		
		try {
			openConn();
			
			sql = "select * from emp where job = ? order by empno";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "MANAGER");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpDTO dto = new EmpDTO();
				
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				
				mgrList.add(dto);
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mgrList;
	}  // getMgrList() 메서드 end
	
	
	// DEPT 테이블의 전체 리스트를 조회하는 메서드
	public List<DeptDTO> getDeptList() {
		
		List<DeptDTO> deptList = new ArrayList<DeptDTO>();
		
		try {
			openConn();
			
			sql = "select * from dept order by deptno";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DeptDTO dto = new DeptDTO();
				
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
				
				deptList.add(dto);
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deptList;
	}  // getDeptList() 메서드 end
	
	
	// EMP 테이블에 사원 등록 폼에서 넘어온 데이터를 등록(추가)하는 메서드.
	public int insertEmp(EmpDTO dto) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "insert into emp values(?, ?, ?, ?, sysdate, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getEmpno());
			
			pstmt.setString(2, dto.getEname());
			
			pstmt.setString(3, dto.getJob());
			
			pstmt.setInt(4, dto.getMgr());
			
			pstmt.setInt(5, dto.getSal());
			
			pstmt.setInt(6, dto.getComm());
			
			pstmt.setInt(7, dto.getDeptno());
			
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}  // insertEmp() 메서드 end
	
	
	
	// EMP 테이블에서 사원번호에 해당하는 사원의 정보를 조회하는 메서드.
	public EmpDTO getContent(int num) {
		
		EmpDTO dto = new EmpDTO();
		
		try {
			openConn();
			
			sql = "select * from emp where empno = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
			}
			
			rs.close(); pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}  // getContent() 메서드 end
	
	
	// EMP 테이블에서 사원번호에 해당하는 사원의 정보를 수정하는 메서드.
	public int updateEmp(EmpDTO dto) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "update emp set job = ?, mgr = ?, "
					+ " sal = ?, comm = ?, deptno = ? "
					+ " where empno = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getJob());
			
			pstmt.setInt(2, dto.getMgr());
			
			pstmt.setInt(3, dto.getSal());
			
			pstmt.setInt(4, dto.getComm());
			
			pstmt.setInt(5, dto.getDeptno());
			
			pstmt.setInt(6, dto.getEmpno());
			
			
			result = pstmt.executeUpdate();
			
			
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}  // updateEmp() 메서드 end
	
	
	// EMP 테이블에서 사원번호에 해당하는 사원을 삭제하는 메서드.
	public int deleteEmp(int no) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "delete from emp where empno = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			result = pstmt.executeUpdate();
			
			pstmt.close(); con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}  // deleteEmp() 메서드 end
	
	
	
	
}
