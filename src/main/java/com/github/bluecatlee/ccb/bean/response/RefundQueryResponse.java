package com.github.bluecatlee.ccb.bean.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.ccb.bean.CCBResponseBody;
import lombok.Data;

import java.util.List;

/**
 * 退款查询响应
 *
 * @Date 2021/2/26 15:29
 */
@Data
public class RefundQueryResponse extends CCBResponseBody {

    /**
     * 当前页次
     */
    @JacksonXmlProperty(localName = "CUR_PAGE")
    private String curPage;

    /**
     * 总页次
     */
    @JacksonXmlProperty(localName = "TPAGE")
    private String tPage;

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
    private List<RefundDetails> details;
//    @JacksonXmlProperty(localName = "LIST")
//    private RefundDetails details;

}
