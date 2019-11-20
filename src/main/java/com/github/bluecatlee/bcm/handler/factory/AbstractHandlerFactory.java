package com.github.bluecatlee.bcm.handler.factory;


import com.github.bluecatlee.bcm.handler.BcmPostHandler;

public abstract class AbstractHandlerFactory {

	public abstract BcmPostHandler create(String type);

}
