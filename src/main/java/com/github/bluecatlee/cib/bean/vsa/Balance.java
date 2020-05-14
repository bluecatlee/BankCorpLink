package com.github.bluecatlee.cib.bean.vsa;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class Balance {

    /**
     *  活期账户余额，和可用余额基本一致，除非存在贷款户等业务上冻结或被控制的金额，总账才会比可用大。
     */
    @JacksonXmlProperty(localName = "BALAMT")
    private String balance;

    /**
     * 日期
     */
    @JacksonXmlProperty(localName = "DTASOF")
    private String date;

}
