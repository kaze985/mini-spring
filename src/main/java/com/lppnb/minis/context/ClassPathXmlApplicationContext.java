package com.lppnb.minis.context;

import com.lppnb.minis.beans.BeansException;
import com.lppnb.minis.beans.factory.BeanFactory;
import com.lppnb.minis.beans.factory.SimpleBeanFactory;
import com.lppnb.minis.beans.factory.config.BeanDefinition;
import com.lppnb.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.lppnb.minis.core.ClassPathXmlResource;
import com.lppnb.minis.core.Resource;

public class ClassPathXmlApplicationContext implements BeanFactory {
    private BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        Resource resource = new ClassPathXmlResource(fileName);
        BeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }

    public Object getBean(String beanName) throws BeansException {
        return beanFactory.getBean(beanName);
    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanDefinition);
    }

}
