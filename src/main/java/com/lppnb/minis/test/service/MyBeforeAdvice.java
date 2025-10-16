package com.lppnb.minis.test.service;

import java.lang.reflect.Method;

import com.lppnb.minis.aop.MethodBeforeAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		log.info("AOP前置通知: 方法{}即将被调用, 目标对象={}", method.getName(), target.getClass().getSimpleName());
	}

}
