package com.lppnb.minis.test;

import com.lppnb.minis.context.ApplicationListener;
import com.lppnb.minis.context.ContextRefreshedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyListener implements ApplicationListener<ContextRefreshedEvent> {
	   @Override
	   public void onApplicationEvent(ContextRefreshedEvent event) {
	      log.info("应用上下文刷新完成, Bean定义数量: {}", event.getApplicationContext().getBeanDefinitionCount());
	   }

}

