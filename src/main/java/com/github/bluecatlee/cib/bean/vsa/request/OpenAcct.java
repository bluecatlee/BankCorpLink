package com.github.bluecatlee.cib.bean.vsa.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.request.ReqParams;
import com.github.bluecatlee.cib.valid.annotation.CibField;
import lombok.Data;

import java.util.List;

/**
 * 虚拟子账户批量开户
 */
@Data
public class OpenAcct extends ReqParams {

    /**
     * 18位主账户 必输
     */
    @JacksonXmlProperty(localName = "MAINACCT")
    @CibField(required = true)
    private String mainAcct;

    /**
     * 总笔数 整数 非必输
     */
    @JacksonXmlProperty(localName = "TOTALCOUNT")
    private String totalCount;

    /**
     * 子账户列表 最多20笔 必输
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "VSAOPENINFO")
    @CibField(required = true)
    private List<VSAOpenInfo> vsaOpenInfos;

    @Override
    public String getBizTag() {
        return "VSABATCHOPENTRNRQ";
    }

    @Override
    public String getRespBizTag() {
        return "VSABATCHOPENTRNRS";
    }

}
