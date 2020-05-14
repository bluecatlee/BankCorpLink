package com.github.bluecatlee.cib.base.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class SignonReq {

    /**
     * 客户端日期时间
     *      YYYY-MM-DD_HH:MM:SS
     *      “_”表示空格
     */
    @JacksonXmlProperty(localName = "DTCLIENT")
    private String clientDateTime;

    /**
     * 企业网银客户号
     *      10位数字字符
     */
    @JacksonXmlProperty(localName = "CID")
    private String cid;

    /**
     * 登录用户名 最长：20位
     */
    @JacksonXmlProperty(localName = "USERID")
    private String userId;

    /**
     * 登录密码 最长：30位
     */
    @JacksonXmlProperty(localName = "USERPASS")
    private String userPass;

    /**
     * USERKEY与(USERID和USERPASS)不同时出现，
     * 由服务器在上一次请求响应中提供，建议使用(USERID和USERPASS)
     */
    @JacksonXmlProperty(localName = "USERKEY")
    private String userKey;

    /**
     * 是否需要服务器产生USERKEY,  填Y、N
     */
    @JacksonXmlProperty(localName = "GENUSERKEY")
    private String genUserKey = "N";

    /**
     * 希望服务器响应信息使用的语言，目前仅支持CHS(中文简体)
     *      非必输
     */
    @JacksonXmlProperty(localName = "LANGUAGE")
    private String language;

    /**
     * 客户端应用程序编码，五个字符
     *      非必输
     */
    @JacksonXmlProperty(localName = "APPID")
    private String appId;

    /**
     * 客户端应用程序版本
     *      非必输
     */
    @JacksonXmlProperty(localName = "APPVER")
    private String appVersion;

}
