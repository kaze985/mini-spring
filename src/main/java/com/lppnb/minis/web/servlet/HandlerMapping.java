package com.lppnb.minis.web.servlet;

import javax.servlet.http.HttpServletRequest;

import com.lppnb.minis.web.method.HandlerMethod;

public interface HandlerMapping {
	HandlerMethod getHandler(HttpServletRequest request) throws Exception;
}
