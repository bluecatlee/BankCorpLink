package com.github.bluecatlee.ccb.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.github.bluecatlee.ccb.redundance.MessagePack;
import lombok.Data;

/**
 * 建设银行基础响应
 *
 * @Date 2021/2/24 14:06
 */
@Data
@JacksonXmlRootElement(localName = "TX")
public class CCBBaseResponse<T extends CCBResponseBody> extends MessagePack {

    /**
     * 请求序列号
     */
    @JacksonXmlProperty(localName = "REQUEST_SN")
    private String requestSn;

    /**
     * 商户号
     */
    @JacksonXmlProperty(localName = "CUST_ID")
    private String custId;

    /**
     * 交易码
     */
    @JacksonXmlProperty(localName = "TX_CODE")
    private String txCode;

    /**
     * 响应码
     */
    @JacksonXmlProperty(localName = "RETURN_CODE")
    private String returnCode;

    /**
     * 响应信息
     */
    @JacksonXmlProperty(localName = "RETURN_MSG")
    private String returnMsg;

    /**
     * 语言
     */
    @JacksonXmlProperty(localName = "LANGUAGE")
    private String language;

    /**
     * 响应体
     */
    @JacksonXmlProperty(localName = "TX_INFO")
    private T ccbResponseBody;

}
