package com.lppnb.minis.test;

import com.lppnb.minis.context.ApplicationListener;
import com.lppnb.minis.context.ContextRefreshedEvent;

public class MyListener implements ApplicationListener<ContextRefreshedEvent> {
	   @Override
	   public void onApplicationEvent(ContextRefreshedEvent event) {
	      System.out.println(".........refreshed.........beans count : " + event.getApplicationContext().getBeanDefinitionCount());
	   }

}

