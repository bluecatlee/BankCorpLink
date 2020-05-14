package com.github.bluecatlee.cib.bean.vsa.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.RespBizAware;
import com.github.bluecatlee.cib.base.request.ReqParams;
import com.github.bluecatlee.cib.bean.vsa.AccountForm;
import com.github.bluecatlee.cib.bean.vsa.IncludeTrans;
import com.github.bluecatlee.cib.valid.annotation.CibField;
import lombok.Data;

/**
 * 虚拟子账户余额及交易明细查询
 */
@Data
public class SubAcctTransDetailQuery extends ReqParams implements RespBizAware {

    /**
     *  查询类型：1－虚拟子账户交易明细查询
     */
    @JacksonXmlProperty(localName = "VATTYPE")
    @CibField(required = true)
    private String type;

    /**
     * 18位实体扣款账户
     */
    @JacksonXmlProperty(localName = "MAINACCT")
    @CibField(required = true)
    private String mainAcct;

    /**
     * 6位虚拟小序号
     */
    @JacksonXmlProperty(localName = "SUBACCT")
    @CibField(required = true)
    private String subAcct;

    /**
     * 付款账户信息
     */
    @JacksonXmlProperty(localName = "ACCTFROM")
    @CibField(required = true)
    private AccountForm acctfrom;

    /**
     * 包含交易流水（未指定起止时间，表示查余额；
     *               若指定起止时间，那么：
     *                  1）开始时间=结束时间；
     *                  2）开始时间早于结束时间，并且结束时间不为当天。建议查询指定某一天的流水，避免网络传输带来的超时）。
     *              日期信息未填写，表示只查询企业内部账户余额。
     */
    @JacksonXmlProperty(localName = "INCTRAN")
    // @CibField(required = true)
    private IncludeTrans inctran;

    @Override
    public String getBizTag() {
        return "VATSTMTTRNRQ";
    }

    @Override
    public String getParamsWrapperTag() {
        return "VATSTMTRQ";
    }

    @Override
    public String getRespBizTag() {
        return "VATSTMTTRNRS";
    }

}
