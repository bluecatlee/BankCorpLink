package com.github.bluecatlee.cib.base;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.github.bluecatlee.cib.base.request.SecuritiesMsgReq;
import com.github.bluecatlee.cib.base.request.SignonMsgReq;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "FOX")
public class BaseReq {

    @JacksonXmlProperty(localName = "SIGNONMSGSRQV1")
    private SignonMsgReq signonMsgReq;

    @JacksonXmlProperty(localName = "SECURITIES_MSGSRQV1")
    private SecuritiesMsgReq securitiesMsgReq;

}
