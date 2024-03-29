package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	
	public abstract ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
