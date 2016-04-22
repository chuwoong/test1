package com.stitch.tumblr.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandInterface {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable;
}
