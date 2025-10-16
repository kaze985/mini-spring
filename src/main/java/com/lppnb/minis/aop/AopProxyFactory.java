package com.lppnb.minis.aop;

public interface AopProxyFactory {
	AopProxy createAopProxy(Object target, PointcutAdvisor adviseor);
}
