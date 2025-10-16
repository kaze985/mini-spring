package com.lppnb.minis.test.service;

import com.lppnb.minis.aop.MethodInterceptor;
import com.lppnb.minis.aop.MethodInvocation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyInterceptor implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		log.info("AOP方法拦截器: 方法{}即将被调用, 目标对象={}, 参数={}", 
			invocation.getMethod().getName(), 
			invocation.getThis().getClass().getSimpleName(),
			invocation.getArguments());
		Object ret=invocation.proceed();
		log.info("AOP方法拦截器: 方法{}执行完成, 返回值={}", 
			invocation.getMethod().getName(), ret);
		
		return ret;
	}

}
