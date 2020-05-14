package com.github.bluecatlee.cib.bean.vsa.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.request.ReqParams;
import com.github.bluecatlee.cib.valid.annotation.CibField;
import lombok.Data;

/**
 * 虚拟子账户签约解约
 */
@Data
public class Sign extends ReqParams {

    /**
     *  18位主账户
     */
    @JacksonXmlProperty(localName = "MAINACCT")
    @CibField(required = true)
    private String mainAcct;

    /**
     *  Y-签约 N-解约
     */
    @JacksonXmlProperty(localName = "SIGNFLG")
    @CibField(required = true)
    private String signFlag;

    @Override
    public String getBizTag() {
        return "VSASIGNTRNRQ";
    }

    @Override
    public String getRespBizTag() {
        return "VSASIGNTRNRS";
    }

}
