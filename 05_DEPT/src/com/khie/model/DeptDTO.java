package com.khie.model;
// 데이터를 전송하는 객체
// DB 상의 DEPT 테이블의 컬럼과 동일하게 멤버 구성

/*
 * ★★★★★★★★★★★★★★★★★★★★★★★★
 * 자바 빈(java Bean)?
 * -DB에 접근하여 테이블의 특정 컬럼(속성)에 값을 저장하거나
 * 저장된 값을 가져올 때 사용하는 클래스.
 * -java Bean == DTO(Data Transfer Object데이터를 전송하는 객체) == VO(Value Object값을 가지고 있는 객체)
 * -구성요소
 * 1. 멤버변수 - DB컬럼명 : 접근지정자(private)
 * 2. setter(): 지정자 메서드
 * 3. getter(): 획득자 메서드
 * 일반적인 DTO는 오직을 갖고 있지 않다 순수한 데이터 객체이며 속성과 그 속성에 접근하기 위한
 * getter,setter 메소드만 가진 클래스를 말한다.
 * DTO는 Data Transfer Object의 약자로, 계층 간 데이터 교환 역할을 한다. 
 * DB에서 꺼낸 데이터를 저장하는 Entity를 가지고 만드는 일종의 Wrapper라고 볼 수 있는데,
 *  Entity를 Controller 같은 클라이언트단과 직접 마주하는 계층에 직접 전달하는 대신 DTO를 사용해 데이터를 교환한다.
 * DTO는 그저 계층간 데이터 교환이 이루어 질 수 있도록 하는 객체이기 때문에,
 *  특별한 로직을 가지지 않는 순수한 데이터 객체여야 한다. 또한 DB에서 꺼낸 값을 DTO에서 임의로 조작할 필요가 없기 때문에 DTO에는 Setter를 만들 필요가 없고 생성자에서 값을 할당한다.
 */

public class DeptDTO {
	
	private int deptno;
	private String dname;
	private String loc;
	

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
