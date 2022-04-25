-- upload 테이블 생성 


create table upload(

	upload_no number(5) primary key,		-- 자료실 글 번호
	upload_writer varchar2(30) not null,	-- 자료실 글 작성자
	upload_title varchar2(200) not null,	-- 자료실 글 제목
	upload_cont varchar2(1500) not null,	-- 자료실 글내용
	upload_pwd varchar2(30) not null,		-- 자료실 글 비밀번호
	upload_file varchar2(500),				-- 자료실 파일명
	upload_hit number(5) default 0,			-- 자료실 글조회수
	upload_date date,						-- 자료실 글 작성일자
	upload_update date						-- 자료실 글 수정일자
);