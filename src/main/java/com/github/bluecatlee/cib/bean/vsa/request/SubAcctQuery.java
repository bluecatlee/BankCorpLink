package com.github.bluecatlee.cib.bean.vsa.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.RespBizAware;
import com.github.bluecatlee.cib.base.request.ReqParams;
import com.github.bluecatlee.cib.valid.annotation.CibField;
import lombok.Data;

/**
 * 子账户信息查询
 */
@Data
public class SubAcctQuery extends ReqParams implements RespBizAware {

    /**
     * 18位主账户
     */
    @JacksonXmlProperty(localName = "MAINACCT")
    @CibField(required = true)
    private String mainAcct;

    /**
     * 子账户:ALL全部子账户 XXXX-具体子账户
     */
    @JacksonXmlProperty(localName = "SUBACCT")
    @CibField(required = true)
    private String subAcct;

    /**
     * 开始行，每行20笔，默认1
     */
    @JacksonXmlProperty(localName = "STARTROW")
    @CibField(required = true)
    private String startRow;

    /**
     * 查询模式，
     *      1-查询某个子账户详细信息，
     *        响应增加详细信息：
     *          利率代号、利率生效日期、利率比例浮动值、利率点数浮动值、执行利率、
     *          计息标识、结息标识、结息周期、首次结息日期、应加应减积数、未结利息、
     *          预算标识、预算额度、预算额度起始日期、预算额度到期日期、额度循环标志、
     *          透资额度、结算账号、主账户户名、主账户余额、子账户汇总余额
     */
    @JacksonXmlProperty(localName = "PATTERN")
    private String pattern;

    @Override
    public String getBizTag() {
        return "VSASUBACCTINFOTRNRQ";
    }

    @Override
    public String getRespBizTag() {
        return "VSASUBACCTINFOTRNRS";
    }

    @Override
    public String getParamsWrapperTag() {
        return "INQUIRYINFO";
    }

    @Override
    public String getResultWrapperTag() {
        return "SUBACCTLIST";
    }

}
