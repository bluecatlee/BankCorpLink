package com.github.bluecatlee.bcm.handler.factory;

import com.github.bluecatlee.bcm.handler.BcmPostHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DefaultHandlerFactory extends AbstractHandlerFactory implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public BcmPostHandler create(String type) {
		return (BcmPostHandler) applicationContext.getBean(type);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
