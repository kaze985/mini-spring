package com.lppnb.minis.aop;

public interface Advisor {
	MethodInterceptor getMethodInterceptor();
	void setMethodInterceptor(MethodInterceptor methodInterceptor);
	Advice getAdvice();
}
