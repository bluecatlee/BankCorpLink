package com.github.bluecatlee.bcm.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "ap")
public class BcmResponse<T> {

    @JacksonXmlProperty(localName = "head")
    private BcmRespHeader header;

    // @JacksonXmlProperty(localName = "body")
    private T body;

    // todo T 泛型上限
}
