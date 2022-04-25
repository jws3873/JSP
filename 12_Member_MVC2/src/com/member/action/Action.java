package com.member.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	//추상매서드
	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
