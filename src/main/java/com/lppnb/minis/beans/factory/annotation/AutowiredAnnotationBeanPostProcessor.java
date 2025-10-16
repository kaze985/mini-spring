package com.lppnb.minis.beans.factory.annotation;

import java.lang.reflect.Field;

import com.lppnb.minis.beans.BeansException;
import com.lppnb.minis.beans.factory.BeanFactory;
import com.lppnb.minis.beans.factory.BeanFactoryAware;
import com.lppnb.minis.beans.factory.config.BeanPostProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor,BeanFactoryAware {
	private BeanFactory beanFactory;
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Object result = bean;
		
		Class<?> clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		if(fields!=null){
			for(Field field : fields){
				boolean isAutowired = field.isAnnotationPresent(Autowired.class);
				if(isAutowired){
					String fieldName = field.getName();
					Object autowiredObj = this.getBeanFactory().getBean(fieldName);
					try {
						field.setAccessible(true);
						field.set(bean, autowiredObj);
						log.debug("自动注入字段: beanName={}, fieldName={}, autowiredType={}", 
							beanName, fieldName, autowiredObj.getClass().getSimpleName());
					} catch (IllegalArgumentException e) {
						log.error("自动注入字段时参数错误: beanName={}, fieldName={}", beanName, fieldName, e);
					} catch (IllegalAccessException e) {
						log.error("自动注入字段时访问权限错误: beanName={}, fieldName={}", beanName, fieldName, e);
					}

				}
			}
		}
		
		return result;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}


}
