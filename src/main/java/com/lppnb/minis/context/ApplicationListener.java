package com.lppnb.minis.context;

import java.util.EventListener;

public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
	void onApplicationEvent(E event);
}
