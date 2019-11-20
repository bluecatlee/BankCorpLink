package com.github.bluecatlee.bcm.bean.protocol;

import com.github.bluecatlee.bcm.annotation.BcmField;
import lombok.Data;

/**
 * 收富通宝－在线签约收款-子协议查询（210406）
 *      企业根据查询条件查询出所有的付款协议（即子协议） (在线签约收款)
 */
@Data
public class QuerySubProtocolReq2 extends QuerySubProtocolReq {

    /**
     * 付款账号 个人卡或对公结算户
     */
    @BcmField(slen = 40)
    private String accNo;

    /**
     * 付款户名
     */
    @BcmField(slen = 60)
    private String accName;

    /**
     * 付款签约起始日期 起始日期与截止日期成对存在
     */
    @BcmField(slen = 8)
    private String qryBegDate;

    /**
     * 付款签约截止日期 起始日期与截止日期成对存在
     */
    @BcmField(slen = 8)
    private String qryEndDate;

    @Override
    public String getTrCode() {
        return "210406";
    }
}
