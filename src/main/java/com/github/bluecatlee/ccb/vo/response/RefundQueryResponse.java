package com.github.bluecatlee.ccb.vo.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.ccb.vo.CCBResponseBody;
import lombok.Data;

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
//    private List<RefundDetails> details;
    @JacksonXmlProperty(localName = "LIST")
    private RefundDetails details;

}
