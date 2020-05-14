package com.github.bluecatlee.cib.bean.vsa.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.RespBizAware;
import com.github.bluecatlee.cib.base.request.ReqParams;
import com.github.bluecatlee.cib.valid.annotation.CibField;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 虚拟子账户内部转账
 */
@Data
public class InnerTransfer extends ReqParams implements RespBizAware {

    /**
     * 实体对公主账户，18位
     */
    @JacksonXmlProperty(localName = "MAINACCT")
    @CibField(required = true)
    private String mainAcct;

    /**
     * 付款方虚拟小序号
     *  最长6位
     */
    @JacksonXmlProperty(localName = "SUBACCT")
    @CibField(required = true)
    private String subAcct;

    /**
     * 付款方虚拟子账号名称
     *  最长60位
     */
    @JacksonXmlProperty(localName = "SUBNAME")
    private String subName;

    /**
     * 收款方虚拟小序号 最长6位
     */
    @JacksonXmlProperty(localName = "TOSUBACCT")
    @CibField(required = true)
    private String toSubAcct;

    /**
     * 收款方虚拟子账号名称
     *  最长60位
     */
    @JacksonXmlProperty(localName = "TOSUBNAME")
    private String toSubName;

    /**
     * 转账金额，Decimal(17,2)
     */
    @JacksonXmlProperty(localName = "TRNAMT")
    @CibField(required = true)
    private BigDecimal transAmt;

    /**
     * 凭证号，可不填，
     * 默认使用电子凭证号
     */
    @JacksonXmlProperty(localName = "CHEQUENUM")
    private String chequeNum;

    /**
     * 转账用途，最大60位，非空
     */
    @JacksonXmlProperty(localName = "PURPOSE")
    @CibField(required = true)
    private String purpose;

    /**
     * 备注，最大60位，可不填
     */
    @JacksonXmlProperty(localName = "MEMO")
    private String memo;

    /**
     * 转账期望日，30天以内，可不填
     * 格式：YYYY-MM-DD，默认当天
     */
    @JacksonXmlProperty(localName = "DTDUE")
    private String expectDate;

    @Override
    public String getBizTag() {
        return "VSAINTRSFTRNRQ";
    }

    @Override
    public String getParamsWrapperTag() {
        return "VSAINTRSFRQ";
    }

    @Override
    public String getRespBizTag() {
        return "VSAINTRSFTRNRS";
    }

    @Override
    public String getResultWrapperTag() {
        return "VSAINTRSFRS";
    }

}
