package com.lppnb.minis.beans.factory.config;

import com.lppnb.minis.beans.BeansException;
import com.lppnb.minis.beans.factory.BeanFactory;

public interface BeanFactoryPostProcessor {
	void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
