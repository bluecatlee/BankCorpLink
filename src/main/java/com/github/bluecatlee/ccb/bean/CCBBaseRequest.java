package com.github.bluecatlee.ccb.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

/**
 * 建设银行基础请求
 *
 * @Date 2021/2/24 13:34
 */
@Data
@JacksonXmlRootElement(localName = "TX")
public class CCBBaseRequest {

    /**
     *  请求序列号   最长16位 只可以使用数字
     */
    @JacksonXmlProperty(localName = "REQUEST_SN")
    private String requestSn;

    /**
     * 商户号
     */
    @JacksonXmlProperty(localName = "CUST_ID")
    private String custId;

    /**
     * 操作员号
     */
    @JacksonXmlProperty(localName = "USER_ID")
    private String userId;

    /**
     * 密码
     */
    @JacksonXmlProperty(localName = "PASSWORD")
    private String password;

    /**
     * 交易码
     */
    @JacksonXmlProperty(localName = "TX_CODE")
    private String txCode;

    /**
     * 语言
     */
    @JacksonXmlProperty(localName = "LANGUAGE")
    private String language = "CN";

    /**
     * 请求体
     */
    @JacksonXmlProperty(localName = "TX_INFO")
    private CCBRequestBody ccbRequestBody;

}
