package com.github.bluecatlee.cib.base.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class BusinessReq {

    /**
     * 客户端交易的唯一标志
     */
    @JacksonXmlProperty(localName = "TRNUID")
    private String trnuid;

    /**
     * 请求体 (封装请求参数)
     */
    @JacksonXmlProperty(localName = "RQBODY")
    private ReqParams reqParams;

}
