package com.github.bluecatlee.cib.base.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class SignonMsgResp {

    @JacksonXmlProperty(localName = "SONRS")
    private SignonResp signonResp;

}
