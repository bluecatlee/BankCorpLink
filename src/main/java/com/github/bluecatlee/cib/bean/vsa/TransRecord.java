package com.github.bluecatlee.cib.bean.vsa;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class TransRecord {

    /**
     * 交易序号  必回，12位
     */
    @JacksonXmlProperty(localName = "SRVRTID")
    private String transId;

    /**
     * 交易类型（借(DEBIT/CREDIT)贷类型）必回
     */
    @JacksonXmlProperty(localName = "TRNTYPE")
    private String transType;

    /**
     * 核心摘要代码 必回
     */
    @JacksonXmlProperty(localName = "TRNCODE")
    private String transCode;

    /**
     * 记账日期  必回
     */
    @JacksonXmlProperty(localName = "DTACCT")
    private String dateAcct;

    /**
     * 交易金额，为负值表示冲正  必回
     */
    @JacksonXmlProperty(localName = "TRNAMT")
    private String transAmount;

    /**
     * 余额（交易结束后的账户余额（历史余额），不能通过此获取当日余额，这和我行会计日期时间有关系。例如11点批处理，则11点后的操作都会记作第二天的转账）  必回
     */
    @JacksonXmlProperty(localName = "BALAMT")
    private String balance;

    /**
     * 币种 （非必回）
     */
    @JacksonXmlProperty(localName = "CURRENCY")
    private String currency;

    /**
     * “摘要简称|用途（来账，往账：用途。1187补录，基本为交易代码）”如果无备注，则只返回” 摘要简称” （非必回）
     */
    @JacksonXmlProperty(localName = "MEMO")
    private String memo;

    /**
     * 对方账号（非必回）
     */
    @JacksonXmlProperty(localName = "CORRELATE_ACCTID")
    private String correlateAcctId;

    /**
     * 对方账户名称（非必回）
     */
    @JacksonXmlProperty(localName = "CORRELATE_NAME")
    private String correlateName;

    /**
     * 本行凭证代号 （非必回）；如果有回复，规则是2位凭证种类+7位凭证代号
     */
    @JacksonXmlProperty(localName = "CHEQUENUM")
    private String chequeNum;

    /**
     * 他行凭证类型  2位（非必回）
     */
    @JacksonXmlProperty(localName = "BILLTYPE")
    private String billType;

    /**
     * 他行凭证号码  最大8位（非必回）
     */
    @JacksonXmlProperty(localName = "BILLNUMBER")
    private String billNumber;

    /**
     * 附加行名  最大50位（非必回）
     */
    @JacksonXmlProperty(localName = "CORRELATE_BANKNAME")
    private String correlateBankName;

    /**
     * 附加行号  12位（非必回）
     */
    @JacksonXmlProperty(localName = "CORRELATE_BANKCODE")
    private String correlateBankCode;

    /**
     * 业务类型  最大20位（非必回）（非必回，摘要代号---业务部门提供）
     */
    @JacksonXmlProperty(localName = "BUSINESSTYPE")
    private String businessType;

    /**
     * 流水唯一标识号，由流水交易日期、核心传票组序号、核心传票组内序号组成，最长50位（非必回）
     */
    @JacksonXmlProperty(localName = "ATTACHINFO")
    private String attachInfo;

}
