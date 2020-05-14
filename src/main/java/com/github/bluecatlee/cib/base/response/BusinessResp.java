package com.github.bluecatlee.cib.base.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.Status;
import com.github.bluecatlee.cib.base.TransferProcessResult;
import lombok.Data;

/**
 * 响应内容业务部分
 * @param <T>
 */
@Data
public class BusinessResp<T extends RespParams> {

    /**
     * 客户端交易的唯一标志
     */
    @JacksonXmlProperty(localName = "TRNUID")
    // @JsonProperty("TRNUID")
    private String trnuid;

    /**
     * 交易处理状态
     */
    @JacksonXmlProperty(localName = "STATUS")
    // @JsonProperty("STATUS")
    private Status status;

    /**
     * 响应体 (封装主响应参数)
     */
    @JacksonXmlProperty(localName = "RSBODY")
    // @JsonProperty("RSBODY")
    private T respParams;

    /**
     * 内部转账指令处理结果
     */
    @JacksonXmlProperty(localName = "XFERPRCSTS")
    // @JsonProperty("XFERPRCSTS")
    private TransferProcessResult xferprcsts;

}
