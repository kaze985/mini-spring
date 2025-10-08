package com.lppnb.minis.context;

import com.lppnb.minis.beans.BeansException;
import com.lppnb.minis.beans.factory.ListableBeanFactory;
import com.lppnb.minis.beans.factory.config.BeanFactoryPostProcessor;
import com.lppnb.minis.beans.factory.config.ConfigurableBeanFactory;
import com.lppnb.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.lppnb.minis.core.env.Environment;
import com.lppnb.minis.core.env.EnvironmentCapable;

public interface ApplicationContext 
		extends EnvironmentCapable, ListableBeanFactory, ConfigurableBeanFactory, ApplicationEventPublisher{
	String getApplicationName();
	long getStartupDate();
	ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
	void setEnvironment(Environment environment);
	Environment getEnvironment();
	void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);
	void refresh() throws BeansException, IllegalStateException;
	void close();
	boolean isActive();

}
