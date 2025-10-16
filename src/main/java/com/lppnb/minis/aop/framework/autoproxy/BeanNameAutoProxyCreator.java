package com.lppnb.minis.aop.framework.autoproxy;

import com.lppnb.minis.aop.AopProxy;
import com.lppnb.minis.aop.AopProxyFactory;
import com.lppnb.minis.aop.DefaultAopProxyFactory;
import com.lppnb.minis.aop.PointcutAdvisor;
import com.lppnb.minis.aop.ProxyFactoryBean;
import com.lppnb.minis.beans.BeansException;
import com.lppnb.minis.beans.factory.BeanFactory;
import com.lppnb.minis.beans.factory.config.BeanPostProcessor;
import com.lppnb.minis.util.PatternMatchUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BeanNameAutoProxyCreator implements BeanPostProcessor{
	String pattern;
	private BeanFactory beanFactory;
	private AopProxyFactory aopProxyFactory;
	private String interceptorName;
	private PointcutAdvisor advisor;
	
	public BeanNameAutoProxyCreator() {
		this.aopProxyFactory = new DefaultAopProxyFactory();
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	
	public void setAdvisor(PointcutAdvisor advisor) {
		this.advisor = advisor;
	}
	
	public void setInterceptorName(String interceptorName) {
		this.interceptorName = interceptorName;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
log.debug("尝试为Bean创建代理: beanName={}", beanName);		
		if (isMatch(beanName, this.pattern)) {
log.info("Bean名称匹配成功，创建代理: beanName={}, pattern={}, targetClass={}", beanName, this.pattern, bean.getClass().getSimpleName());		
//			initializeAdvisor();
//			Object ret = getSingletonInstance(bean);
//System.out.println(" created proxy  " + ret);	
//			bean = ret;
//			return ret;
//		}
//		return bean;
			ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
			proxyFactoryBean.setTarget(bean);
			proxyFactoryBean.setBeanFactory(beanFactory);
			proxyFactoryBean.setAopProxyFactory(aopProxyFactory);
			proxyFactoryBean.setInterceptorName(interceptorName);
//			Object ret = null;
//			try {
//				ret = proxyFactoryBean.getObject();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			bean = proxyFactoryBean;
			return proxyFactoryBean;
		}
		else {
			return bean;
		}

	
	}
	

	
	protected AopProxy createAopProxy(Object target) {
		return this.aopProxyFactory.createAopProxy(target,this.advisor);
	}
	protected Object getProxy(AopProxy aopProxy) {
		return aopProxy.getProxy();
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return null;
	}

	protected boolean isMatch(String beanName, String mappedName) {
log.debug("检查Bean名称是否匹配: beanName={}, pattern={}", beanName, mappedName);		
		return PatternMatchUtils.simpleMatch(mappedName, beanName);
	}


}
