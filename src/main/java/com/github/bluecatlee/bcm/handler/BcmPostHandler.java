package com.github.bluecatlee.bcm.handler;

import com.github.bluecatlee.bcm.response.BcmRespHeader;

public interface BcmPostHandler {

    Object handle(BcmRespHeader header);

}
