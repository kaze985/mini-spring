package com.lppnb.minis.test;

import com.lppnb.minis.beans.BeansException;
import com.lppnb.minis.beans.factory.BeanFactory;
import com.lppnb.minis.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	@Override
	public void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println(".........MyBeanFactoryPostProcessor...........");
		
	}

}
