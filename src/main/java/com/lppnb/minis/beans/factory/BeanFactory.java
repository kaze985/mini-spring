package com.lppnb.minis.beans.factory;

import com.lppnb.minis.beans.factory.config.BeanDefinition;
import com.lppnb.minis.beans.BeansException;

public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;

    void registerBeanDefinition(BeanDefinition beanDefinition);
}
