package com.lppnb.minis.context;

import java.util.ArrayList;
import java.util.List;

import com.lppnb.minis.beans.BeansException;
import com.lppnb.minis.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.lppnb.minis.beans.factory.config.BeanFactoryPostProcessor;
import com.lppnb.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.lppnb.minis.beans.factory.support.DefaultListableBeanFactory;
import com.lppnb.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.lppnb.minis.core.ClassPathXmlResource;
import com.lppnb.minis.core.Resource;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
    DefaultListableBeanFactory beanFactory;
    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors =
            new ArrayList<BeanFactoryPostProcessor>();

    public ClassPathXmlApplicationContext(String fileName){
        this(fileName, true);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh){
        Resource res = new ClassPathXmlResource(fileName);
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(res);

        this.beanFactory = bf;

        if (isRefresh) {
            try {
                refresh();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void registerListeners() {
        ApplicationListener listener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    public void initApplicationEventPublisher() {
        ApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(aep);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
    }

    @Override
    public void registerBeanPostProcessors(ConfigurableListableBeanFactory bf) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    @Override
    public void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    public void finishRefresh() {
        publishEvent(new ContextRefreshEvent("Context Refreshed..."));

    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);

    }


}