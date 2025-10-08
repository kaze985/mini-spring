package com.lppnb.minis.context;

import com.lppnb.minis.beans.BeansException;

public interface ApplicationContextAware {
	void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
