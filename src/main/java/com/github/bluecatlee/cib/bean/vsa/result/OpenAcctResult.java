package com.github.bluecatlee.cib.bean.vsa.result;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.response.RespParams;
import lombok.Data;

import java.util.List;

@Data
public class OpenAcctResult extends RespParams {

    /**
      *  18位主账户
     */
    @JacksonXmlProperty(localName = "MAINACCT")
    // @JsonProperty("MAINACCT")
    private String mainAcct;

    /**
     *  主账户户名
     */
    @JacksonXmlProperty(localName = "ACCTNAME")
    // @JsonProperty("ACCTNAME")
    private String acctName;

    /**
     *  总笔数，整数
     */
    @JacksonXmlProperty(localName = "TOTALCOUNT")
    // @JsonProperty("TOTALCOUNT")
    private String totalCount;

    /**
     *  子账户响应列表，最多20笔
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "VSAOPENINFO")
    // @JsonProperty("VSAOPENINFO")
    private List<VSAOpenInfoResult> vsaOpenInfos;



}
