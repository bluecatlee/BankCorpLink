package com.github.bluecatlee.cib.bean.vsa;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.valid.annotation.CibField;
import lombok.Builder;
import lombok.Data;

/**
 * 包含交易流水
 */
@Data
@Builder
public class IncludeTrans {

    /**
     *  开始时间 格式：YYYY-MM-DD
     */
    @JacksonXmlProperty(localName = "DTSTART")
    @CibField(required = true)
    private String dateStart;

    /**
     *  结束时间 格式：YYYY-MM-DD
     */
    @JacksonXmlProperty(localName = "DTEND")
    @CibField(required = true)
    private String dateEnd;

    /**
     *  请求响应的页数（代表从第几页开始查询），每页100条明细
     */
    @JacksonXmlProperty(localName = "PAGE")
    @CibField(required = true)
    private String page;

}
