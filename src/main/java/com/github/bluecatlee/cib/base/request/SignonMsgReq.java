package com.github.bluecatlee.cib.base.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class SignonMsgReq {

    @JacksonXmlProperty(localName = "SONRQ")
    private SignonReq signonReq;

}
