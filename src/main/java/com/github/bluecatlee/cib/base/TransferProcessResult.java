package com.github.bluecatlee.cib.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * 内部转账指令处理结果
 */
@Data
public class TransferProcessResult {

    /**
     * 指令状态编码
     */
    @JacksonXmlProperty(localName = "XFERPRCCODE")
    @JsonProperty("XFERPRCCODE")
    private String code;

    /**
     * 指令处理时间
     */
    @JacksonXmlProperty(localName = "DTXFERPRC")
    @JsonProperty("DTXFERPRC")
    private String date;

    /**
     * 指令处理信息（非必回）
     */
    @JacksonXmlProperty(localName = "MESSAGE")
    @JsonProperty("MESSAGE")
    private String message;

}
