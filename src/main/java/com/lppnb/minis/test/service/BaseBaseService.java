package com.lppnb.minis.test.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseBaseService {
	private AServiceImpl as;
	
	public AServiceImpl getAs() {
		return as;
	}
	public void setAs(AServiceImpl as) {
		this.as = as;
	}
	public BaseBaseService() {
	}
	public void sayHello() {
		log.info("BaseBaseService问候消息");
	}
	
	public void init() {
		log.info("BaseBaseService初始化方法被调用");
	}
}
