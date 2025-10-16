package com.lppnb.minis.test.service;

import com.lppnb.minis.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseService {
	@Autowired
	private BaseBaseService bbs;
	
	public BaseBaseService getBbs() {
		return bbs;
	}
	public void setBbs(BaseBaseService bbs) {
		this.bbs = bbs;
	}
	public BaseService() {
	}
	public void sayHello() {
		log.info("BaseService问候消息");
		bbs.sayHello();
	}
	public String getHello() {
		return "Base Service get Hello.";
	}
}
