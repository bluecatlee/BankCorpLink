package com.github.bluecatlee.cib.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Status {

    /**
     * 结果处理码
     */
    @JacksonXmlProperty(localName = "CODE")
    @JsonProperty("CODE")
    private String code;

    /**
     * 处理结果等级(INFO/WARN/ERROR)
     */
    @JacksonXmlProperty(localName = "SEVERITY")
    @JsonProperty("SEVERITY")
    private String severity;

    /**
     * 信息描述
     */
    @JacksonXmlProperty(localName = "MESSAGE")
    @JsonProperty("MESSAGE")
    private String message;

}
