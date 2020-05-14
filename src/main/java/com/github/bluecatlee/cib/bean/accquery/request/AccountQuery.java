package com.github.bluecatlee.cib.bean.accquery.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.request.ReqParams;
import com.github.bluecatlee.cib.valid.annotation.CibField;
import lombok.Data;

/**
 * 账户信息查询
 */
@Data
public class AccountQuery extends ReqParams {

    /**
     * 付款人账户
     */
    @JacksonXmlProperty(localName = "ACCTID")
    @CibField(required = true)
    private String acctid;

    @Override
    public String getBizTag() {
        return "ACCOUNTQUERYTRNRQ";
    }

    @Override
    public String getRespBizTag() {
        return "ACCOUNTQUERYTRNRS";
    }

}
