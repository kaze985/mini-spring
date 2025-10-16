package com.lppnb.minis.test;

import com.lppnb.minis.beans.BeansException;
import com.lppnb.minis.beans.factory.BeanFactory;
import com.lppnb.minis.beans.factory.config.BeanPostProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		log.debug("Bean初始化前处理: beanName={}, beanClass={}", beanName, bean.getClass().getSimpleName());
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		log.debug("Bean初始化后处理: beanName={}, beanClass={}", beanName, bean.getClass().getSimpleName());
		return bean;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		// TODO Auto-generated method stub
		
	}

}
