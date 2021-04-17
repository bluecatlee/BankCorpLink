package com.github.bluecatlee.ccb.bean.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.ccb.bean.CCBResponseBody;
import lombok.Data;

import java.util.List;

/**
 * 支付流水查询响应
 *
 * @Date 2021/2/25 14:12
 */
@Data
public class PayQueryResponse extends CCBResponseBody {

    /**
     * 当前页次
     */
    @JacksonXmlProperty(localName = "CUR_PAGE")
    private String curPage;

    /**
     * 总页次
     */
    @JacksonXmlProperty(localName = "PAGE_COUNT")
    private String pageCount;

    /**
     * 提示
     */
    @JacksonXmlProperty(localName = "NOTICE")
    private String notice;

    /**
     * 明细
     */
    @JacksonXmlProperty(localName = "LIST")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<PayDetails> details;
//    @JacksonXmlProperty(localName = "LIST")
//    private PayDetails details;

}
