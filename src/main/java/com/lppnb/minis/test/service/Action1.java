package com.lppnb.minis.test.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Action1 implements IAction {

	@Override
	public void doAction() {
		log.info("执行Action1的doAction方法");
	}

	@Override
	public void doSomething() {
		log.info("执行Action1的doSomething方法");
	}

}
