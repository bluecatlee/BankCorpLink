package com.github.bluecatlee.cib.base.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.Status;
import lombok.Data;

@Data
public class SignonResp {

    /**
     * 交易处理状态
     */
    @JacksonXmlProperty(localName = "STATUS")
    private Status status;

    /**
     * 服务端日期时间，YYYY-MM-DD HH:MM:SS
     */
    @JacksonXmlProperty(localName = "DTSERVER")
    private String serverDateTime;

    /**
     * 客户端要求生成USERKEY时发送key值
     *      非必回，仅在GENUSERKEY为”Y”时必回
     */
    @JacksonXmlProperty(localName = "USERKEY")
    private String userKey;

    /**
     * USERKEY的有效时间(服务器时间)
     *      非必回，仅在GENUSERKEY为”Y”时必回
     */
    @JacksonXmlProperty(localName = "TSKEYEXPIRE")
    private String userKeyExpire;

    /**
     * 服务器响应信息使用的语言，目前仅提供CHS(中文简体)，可选
     *      非必回
     */
    @JacksonXmlProperty(localName = "LANGUAGE")
    private String language;

    /**
     * 服务器需要保存会话COOKIE，则发送，否则不发送，客户端在下次请求中应包含
     *      非必回
     */
    @JacksonXmlProperty(localName = "SESSCOOKIE")
    private String sesscookie;

}
