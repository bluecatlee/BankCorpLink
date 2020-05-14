package com.github.bluecatlee.cib.base.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class SecuritiesMsgResp {

    @JacksonXmlProperty(localName = "ACCOUNTQUERYTRNRS")
    @JsonProperty("ACCOUNTQUERYTRNRS")
    private BusinessResp businessResp;

}
