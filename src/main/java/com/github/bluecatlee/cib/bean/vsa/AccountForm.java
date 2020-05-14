package com.github.bluecatlee.cib.bean.vsa;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.valid.annotation.CibField;
import lombok.Data;

@Data
public class AccountForm {

    /**
     * 18位实体扣款账户, 必输
     */
    @JacksonXmlProperty(localName = "ACCTID")
    @CibField(required = true)
    private String acctId;

    /**
     * 虚拟子账户名称(可选), 最大50位
     */
    @JacksonXmlProperty(localName = "NAME")
    private String name;

    /**
     * 开户行名称(可选) 未限制，仅在报文中体现
     */
    @JacksonXmlProperty(localName = "BANKDESC")
    private String bankDesc;

    /**
     * 兑付城市(可选) 未限制，仅在报文中体现
     */
    @JacksonXmlProperty(localName = "CITY")
    private String city;

}
