package com.lppnb.minis.beans.factory.xml;

import org.dom4j.Element;

import com.lppnb.minis.beans.factory.BeanFactory;
import com.lppnb.minis.beans.factory.config.BeanDefinition;
import com.lppnb.minis.core.Resource;

public class XmlBeanDefinitionReader {
    private BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String id = element.attributeValue("id");
            String className = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(id, className);
            beanFactory.registerBeanDefinition(beanDefinition);
        }
    }
}
