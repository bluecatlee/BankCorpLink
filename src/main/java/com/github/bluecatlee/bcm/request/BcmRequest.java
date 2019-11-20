package com.github.bluecatlee.bcm.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JacksonXmlRootElement(localName = "ap")
public class BcmRequest {

    @JacksonXmlProperty(localName = "head")
    private BcmReqHeader header;

    @JacksonXmlElementWrapper(useWrapping = false) // 集合外层不包装
    @JacksonXmlProperty(localName = "body")
    private List<BcmReqParam> params;

}
