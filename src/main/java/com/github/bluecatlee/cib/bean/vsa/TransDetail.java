package com.github.bluecatlee.cib.bean.vsa;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class TransDetail {

    /**
     *  起始日期，必回YYYY-MM-DD
     */
    @JacksonXmlProperty(localName = "DTSTART")
    private String dateStart;

    /**
     *  终止日期，必回YYYY-MM-DD
     */
    @JacksonXmlProperty(localName = "DTEND")
    private String dateEnd;

    /**
     * 交易记录（可选）
     */
    @JacksonXmlProperty(localName = "STMTTRN")
    private TransRecord transRecord;

    /**
     * 总账余额， 必回
     */
    @JacksonXmlProperty(localName = "LEDGERBAL")
    private Balance ledgerBalance;

    /**
     * 可用余额
     */
    @JacksonXmlProperty(localName = "AVAILBAL")
    private Balance availBalance;


}
