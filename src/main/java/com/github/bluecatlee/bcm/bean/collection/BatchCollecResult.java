package com.github.bluecatlee.bcm.bean.collection;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class BatchCollecResult {

    /**
     * 网银批次号
     */
    @JacksonXmlProperty(localName = "ebank_batflw")
    private String ebankBatFlw;

}
