package com.github.bluecatlee.cib.bean.vsa.result;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.response.RespParams;
import com.github.bluecatlee.cib.bean.vsa.AccountForm;
import com.github.bluecatlee.cib.bean.vsa.TransDetail;
import lombok.Data;

import java.util.List;

@Data
public class SubAcctTransDetailQueryResult extends RespParams {

    /**
     * 查询类型：1－虚拟子账户交易明细查询
     */
    @JacksonXmlProperty(localName = "VATTYPE")
    private String type;

    /**
     * 18位实体扣款账户
     */
    @JacksonXmlProperty(localName = "MAINACCT")
    private String mainAcct;

    /**
     * 6位虚拟小序号
     */
    @JacksonXmlProperty(localName = "SUBACCT")
    private String subAcct;

    /**
     *  默认货币代码
     */
    @JacksonXmlProperty(localName = "CURDEF")
    private String currency;

    /**
     * 付款账户信息
     */
    @JacksonXmlProperty(localName = "ACCTFROM")
    private AccountForm accountForm;

    @JacksonXmlProperty(localName = "TRANLIST", namespace = "MORE")
    private List<TransDetail> transDetailList;

}
