package com.github.bluecatlee.cib.bean.vsa.result;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.response.RespParams;
import com.github.bluecatlee.cib.bean.vsa.SubAcctInfo;
import lombok.Data;

import java.util.List;

@Data
public class SubAcctQueryResult extends RespParams {

    @JacksonXmlElementWrapper(localName = "SUBACCTLIST", namespace = "MORE")
    @JacksonXmlProperty(localName = "SUBACCTINFO")
    private List<SubAcctInfo> subAcctInfoList;

}
