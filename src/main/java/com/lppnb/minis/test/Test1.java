package com.lppnb.minis.test;

import com.lppnb.minis.beans.BeansException;
import com.lppnb.minis.context.ClassPathXmlApplicationContext;
import com.lppnb.minis.test.service.AService;
import com.lppnb.minis.test.service.BaseService;

public class Test1 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	    AService aService;
	    BaseService bService;
		try {
			aService = (AService)ctx.getBean("aservice");
		    aService.sayHello();
		    
		    bService = (BaseService)ctx.getBean("baseservice");
		    bService.sayHello();
		} catch (BeansException e) {
			e.printStackTrace();
		}
	}

}
