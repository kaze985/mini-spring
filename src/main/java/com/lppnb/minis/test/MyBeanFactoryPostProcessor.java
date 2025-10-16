package com.lppnb.minis.test;

import com.lppnb.minis.beans.BeansException;
import com.lppnb.minis.beans.factory.BeanFactory;
import com.lppnb.minis.beans.factory.config.BeanFactoryPostProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	@Override
	public void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException {
		log.info("自定义BeanFactoryPostProcessor处理器执行");
	}

}
