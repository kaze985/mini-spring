package com.lppnb.minis.web.method.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import com.lppnb.minis.beans.BeansException;
import com.lppnb.minis.context.ApplicationContext;
import com.lppnb.minis.context.ApplicationContextAware;
import com.lppnb.minis.web.bind.annotation.RequestMapping;
import com.lppnb.minis.web.context.WebApplicationContext;
import com.lppnb.minis.web.method.HandlerMethod;
import com.lppnb.minis.web.servlet.HandlerMapping;

public class RequestMappingHandlerMapping implements HandlerMapping,ApplicationContextAware {
	ApplicationContext applicationContext;
	private MappingRegistry mappingRegistry = null;
	
	public RequestMappingHandlerMapping() {
	}
	
    protected void initMappings() {
    	Class<?> clz = null;
    	Object obj = null;
    	String[] controllerNames = this.applicationContext.getBeanDefinitionNames();
    	for (String controllerName : controllerNames) {
			try {
				clz = Class.forName(controllerName);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				obj = this.applicationContext.getBean(controllerName);
			} catch (BeansException e) {
				e.printStackTrace();
			}
    		Method[] methods = clz.getDeclaredMethods();
    		if(methods!=null){
    			for(Method method : methods){
    				boolean isRequestMapping = method.isAnnotationPresent(RequestMapping.class);
    				if (isRequestMapping){
    					String methodName = method.getName();
    					String urlmapping = method.getAnnotation(RequestMapping.class).value();
    					this.mappingRegistry.getUrlMappingNames().add(urlmapping);
    					this.mappingRegistry.getMappingObjs().put(urlmapping, obj);
    					this.mappingRegistry.getMappingMethods().put(urlmapping, method);
    					this.mappingRegistry.getMappingMethodNames().put(urlmapping, methodName);
    					this.mappingRegistry.getMappingClasses().put(urlmapping, clz);
    				}
    			}
    		}
    	}

    }


	@Override
	public HandlerMethod getHandler(HttpServletRequest request) throws Exception {
		if (this.mappingRegistry == null) { //to do initialization
			this.mappingRegistry = new MappingRegistry();
			initMappings();
		}
		
		String sPath = request.getServletPath();
	
		if (!this.mappingRegistry.getUrlMappingNames().contains(sPath)) {
			return null;
		}

		Method method = this.mappingRegistry.getMappingMethods().get(sPath);
		Object obj = this.mappingRegistry.getMappingObjs().get(sPath);
		Class<?> clz = this.mappingRegistry.getMappingClasses().get(sPath);
		String methodName = this.mappingRegistry.getMappingMethodNames().get(sPath);
	
		HandlerMethod handlerMethod = new HandlerMethod(method, obj, clz, methodName);
		
		return handlerMethod;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
