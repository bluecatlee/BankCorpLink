package com.github.bluecatlee.cib.bean.vsa.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class VSAOpenInfoResult {

    /**
     *  子账户，必须为6位数字且在010000-999989之间
     */
    @JacksonXmlProperty(localName = "SUBACCT")
    @JsonProperty("SUBACCT")
    private String subAcct;

    /**
     *  子账户名称
     */
    @JacksonXmlProperty(localName = "SUBNAME")
    @JsonProperty("SUBNAME")
    private String subName;

    /**
     *  单笔核心交易处理结果
     */
    @JacksonXmlProperty(localName = "STATUS")
    @JsonProperty("STATUS")
    private String status;

    /**
     *  单笔核心交易处理结果信息
     */
    @JacksonXmlProperty(localName = "MSG")
    @JsonProperty("MSG")
    private String msg;

}
