package com.github.bluecatlee.cib.bean.vsa.result;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.response.RespParams;
import lombok.Data;

@Data
public class SignResult extends RespParams {

    /**
     *  18位主账户
     */
    @JacksonXmlProperty(localName = "MAINACCT")
    private String mainAcct;

    /**
     *  账户名称
     */
    @JacksonXmlProperty(localName = "ACCTNAME")
    private String acctName;

    /**
     *  Y-签约 N-解约
     */
    @JacksonXmlProperty(localName = "SIGNFLG")
    private String signFlag;

    /**
     *  操作结果，SUCC成功、FAIL失败
     */
    @JacksonXmlProperty(localName = "RESULT")
    private String result;

    /**
     *  处理结果信息
     */
    @JacksonXmlProperty(localName = "MSG")
    private String msg;

}
