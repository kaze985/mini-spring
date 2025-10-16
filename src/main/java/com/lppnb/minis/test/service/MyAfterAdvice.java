package com.lppnb.minis.test.service;

import java.lang.reflect.Method;

import com.lppnb.minis.aop.AfterReturningAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyAfterAdvice implements AfterReturningAdvice{
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		log.info("AOP后置通知: 方法{}执行完成, 目标对象={}, 返回值={}", 
			method.getName(), target.getClass().getSimpleName(), returnValue);
	}

}
