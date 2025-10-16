package com.lppnb.minis.aop;

public interface PointcutAdvisor extends Advisor {
	Pointcut getPointcut();
}