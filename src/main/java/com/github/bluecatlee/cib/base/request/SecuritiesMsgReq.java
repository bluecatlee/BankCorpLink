package com.github.bluecatlee.cib.base.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class SecuritiesMsgReq {

    @JacksonXmlProperty
    private BusinessReq businessReq;

}
