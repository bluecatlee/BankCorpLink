package com.github.bluecatlee.bcm.bean.protocol;

import com.github.bluecatlee.bcm.annotation.BcmField;
import com.github.bluecatlee.bcm.request.BcmReqParam;
import lombok.Data;

/**
 * 收富通宝－在线签约收款-子协议查询（210403）
 *      企业根据收款协议编码查询所有有效的付款协议 （即子协议） (在线签约收款)
 */
@Data
public class QuerySubProtocolReq extends BcmReqParam {

    /**
     * 收款协议编号
     */
    @BcmField(required = true, slen = 30)
    private String cagrNo;

    /**
     * 查询笔数 一次最多不超过50笔
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
        return "210403";
    }
}
