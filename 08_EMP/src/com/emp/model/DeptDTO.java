package com.emp.model;

// DEPT 테이블의 컬럼과 동일하게 멤버변수 설정.

public class DeptDTO {

	private int deptno;       // 부서 번호
	private String dname;     // 부서명
	private String loc;       // 부서 위치
	
	
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}
