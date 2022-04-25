package com.board2.action;

public class ActionForward {

	private boolean Redirect;
	private String path;
	
	
	public boolean isRedirect() {
		return Redirect;
	}
	public void setRedirect(boolean redirect) {
		Redirect = redirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
