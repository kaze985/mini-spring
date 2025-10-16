package com.lppnb.minis.context;

import java.util.ArrayList;
import java.util.List;

import com.lppnb.minis.beans.BeansException;
import lombok.extern.slf4j.Slf4j;
import com.lppnb.minis.beans.factory.config.BeanDefinition;
import com.lppnb.minis.beans.factory.config.BeanFactoryPostProcessor;
import com.lppnb.minis.beans.factory.config.BeanPostProcessor;
import com.lppnb.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.lppnb.minis.beans.factory.support.DefaultListableBeanFactory;
import com.lppnb.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.lppnb.minis.core.ClassPathXmlResource;
import com.lppnb.minis.core.Resource;

@Slf4j
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
				log.error("应用上下文刷新时发生IllegalStateException", e);
			} catch (BeansException e) {
				log.error("应用上下文刷新时发生BeansException", e);
			}
        }
    }

	@Override
	public
	void registerListeners() {
		String[] bdNames = this.beanFactory.getBeanDefinitionNames();
		for (String bdName : bdNames) {
			Object bean = null;
			try {
				bean = getBean(bdName);
			} catch (BeansException e1) {
				log.warn("注册监听器时获取Bean失败: beanName={}", bdName, e1);
			}

			if (bean instanceof ApplicationListener) {
				this.getApplicationEventPublisher().addApplicationListener((ApplicationListener<?>) bean);
			}
		}

	}

	@Override
	public
	void initApplicationEventPublisher() {
		ApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
		this.setApplicationEventPublisher(aep);
	}

	@Override
	public
	void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
		
		String[] bdNames = this.beanFactory.getBeanDefinitionNames();
		for (String bdName : bdNames) {
			BeanDefinition bd = this.beanFactory.getBeanDefinition(bdName);
			String clzName = bd.getClassName();
			Class<?> clz = null;
			try {
				clz = Class.forName(clzName);
			} catch (ClassNotFoundException e1) {
				log.error("无法找到BeanFactoryPostProcessor类: className={}", clzName, e1);
				continue;
			}
			if (BeanFactoryPostProcessor.class.isAssignableFrom(clz)) {
					try {
						this.beanFactoryPostProcessors.add((BeanFactoryPostProcessor) clz.newInstance());
					} catch (InstantiationException e) {
						log.error("实例化BeanFactoryPostProcessor失败: className={}", clzName, e);
					} catch (IllegalAccessException e) {
						log.error("访问BeanFactoryPostProcessor失败: className={}", clzName, e);
					}
			}
		}
		for (BeanFactoryPostProcessor processor : this.beanFactoryPostProcessors) {
			try {
				processor.postProcessBeanFactory(bf);
			} catch (BeansException e) {
				log.error("BeanFactoryPostProcessor处理失败: processor={}", processor.getClass().getSimpleName(), e);
			}
		}
	}

	@Override
	public
	void registerBeanPostProcessors(ConfigurableListableBeanFactory bf) {
log.debug("开始注册BeanPostProcessor");		
		String[] bdNames = this.beanFactory.getBeanDefinitionNames();
		for (String bdName : bdNames) {
			BeanDefinition bd = this.beanFactory.getBeanDefinition(bdName);
			String clzName = bd.getClassName();
			Class<?> clz = null;
			try {
				clz = Class.forName(clzName);
			} catch (ClassNotFoundException e1) {
				log.error("无法找到BeanPostProcessor类: className={}", clzName, e1);
				continue;
			}
			if (BeanPostProcessor.class.isAssignableFrom(clz)) {
				log.debug("注册BeanPostProcessor: className={}", clzName);		
					try {
						//this.beanFactory.addBeanPostProcessor((BeanPostProcessor) clz.newInstance());
						this.beanFactory.addBeanPostProcessor((BeanPostProcessor)(this.beanFactory.getBean(bdName)));
					} catch (BeansException e) {
						log.error("添加BeanPostProcessor失败: beanName={}", bdName, e);
					}
			}
		}
	}

	@Override
	public
	void onRefresh() {
		this.beanFactory.refresh();
	}

	@Override
	public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
		return this.beanFactory;
	}

	@Override
	public void addApplicationListener(ApplicationListener<?> listener) {
		this.getApplicationEventPublisher().addApplicationListener(listener);
		
	}

	@Override
	public
	void finishRefresh() {
		publishEvent(new ContextRefreshedEvent(this));
		
	}

	@Override
	public void publishEvent(ApplicationEvent event) {
		this.getApplicationEventPublisher().publishEvent(event);
		
	}
   
    
}
