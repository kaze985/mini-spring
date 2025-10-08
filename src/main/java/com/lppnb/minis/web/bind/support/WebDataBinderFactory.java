package com.lppnb.minis.web.bind.support;

import javax.servlet.http.HttpServletRequest;

import com.lppnb.minis.web.bind.WebDataBinder;

public class WebDataBinderFactory {
	public WebDataBinder createBinder(HttpServletRequest request, Object target, String objectName) {
		WebDataBinder wbd= new WebDataBinder(target,objectName);
		initBinder(wbd, request);
		return wbd;
	}
	protected void initBinder(WebDataBinder dataBinder, HttpServletRequest request){
	}
}
