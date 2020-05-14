package com.github.bluecatlee.cib.bean.vsa.result;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.response.RespParams;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InnerTransferResult extends RespParams {

    /**
     * 服务器该笔交易的唯一标识
     */
    @JacksonXmlProperty(localName = "SRVRID")
    private String serviceId;

    /**
     * 实体对公主账户，18位
     */
    @JacksonXmlProperty(localName = "MAINACCT")
    private String mainAcct;

    /**
     * 付款方虚拟小序号
     * 最长6位
     */
    @JacksonXmlProperty(localName = "SUBACCT")
    private String subAcct;

    /**
     * 付款方虚拟子账号名称
     * 最长60位
     */
    @JacksonXmlProperty(localName = "SUBNAME")
    private String subName;

    /**
     * 收款方虚拟小序号
     * 最长6位
     */
    @JacksonXmlProperty(localName = "TOSUBACCT")
    private String toSubAcct;

    /**
     * 收款方虚拟子账号名称
     * 最长60位
     */
    @JacksonXmlProperty(localName = "TOSUBNAME")
    private String toSubName;

    /**
     * 转账金额，Decimal(17,2)
     */
    @JacksonXmlProperty(localName = "TRNAMT")
    private BigDecimal transAmount;

    /**
     * 凭证号
     */
    @JacksonXmlProperty(localName = "CHEQUENUM")
    private String chequeNum;

    /**
     * 转账用途，最大60位
     */
    @JacksonXmlProperty(localName = "PURPOSE")
    private String purpose;

    /**
     * 备注，最大60位
     */
    @JacksonXmlProperty(localName = "MEMO")
    private String memo;

    /**
     * 转账期望日，30天以内，
     * 格式：YYYY-MM-DD，默认当天
     */
    @JacksonXmlProperty(localName = "DTDUE")
    private String date;
}
