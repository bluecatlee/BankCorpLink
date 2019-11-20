package com.github.bluecatlee.bcm.bean.collection;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.bcm.annotation.BcmField;
import com.github.bluecatlee.bcm.request.BcmReqParam;
import lombok.Data;

/**
 * 收富通宝－在线签约收款-批量代收结果查询（210402）
 */
@Data
public class QueryBatchCollecReq extends BcmReqParam {

    /**
     * 查询标志
     *      流水号类型
     *      ‘1’：流水号为企业凭证号
     *      ‘2’：流水号为网银批次流水号
     */
    @BcmField(required = true, slen = 1)
    @JacksonXmlProperty(localName = "query_flag")
    private String queryFlag = "1";

    /**
     * 流水号
     */
    @BcmField(slen = 28)
    private String flwno;

    /**
     * 查询笔数 默认值50笔明细,一次最多不超过50笔
     */
    @BcmField(dlen = 8)
    private Integer pageSize;

    /**
     * 起始记录数 从0开始
     */
    @BcmField(dlen = 8)
    private Integer beginPos;

    @Override
    public String getTrCode() {
        return "210402";
    }
}
