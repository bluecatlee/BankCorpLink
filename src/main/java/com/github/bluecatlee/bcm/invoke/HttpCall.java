package com.github.bluecatlee.bcm.invoke;

import com.github.bluecatlee.bcm.constant.Constants;
import com.github.bluecatlee.bcm.exception.BcmException;
import com.github.bluecatlee.bcm.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpCall implements Call {

    private String host;
    private Integer port;

    public HttpCall(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    /**
     * http请求
     *  所有请求均为post
     * @param request
     * @return
     */
    @Override
    public String execute(String request) {
        try {
            log.debug("BCM http call request:" + request);
            String result = OkHttpUtils.post("http://" + host + ":" + port, request.getBytes(Constants.DEFAULT_CHARSET));
            log.debug("BCM http call request:" + result);
            return result;
        } catch(Exception e) {
            throw new BcmException(e);
        }
    }

}
