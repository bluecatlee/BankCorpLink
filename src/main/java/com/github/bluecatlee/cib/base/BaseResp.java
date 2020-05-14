package com.github.bluecatlee.cib.base;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.github.bluecatlee.cib.base.response.SecuritiesMsgResp;
import com.github.bluecatlee.cib.base.response.SignonMsgResp;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "FOX")
public class BaseResp {

    @JacksonXmlProperty(localName = "SIGNONMSGSRSV1")
    private SignonMsgResp signonMsgRsp;

    @JacksonXmlProperty(localName = "SECURITIES_MSGSRSV1")
    private SecuritiesMsgResp securitiesMsgResp;

}
