package com.github.bluecatlee.bcm.bean.info;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class AccountInfoResp {

    /**
     * 户名(C60)|账 号(C40)|币种 (C3)| 余额 (N14.2)|可用 余额(N14.2)| 开 户 日 期 (C8)| 账户类 型 (C1)| 开户 行(C60)|错误 信息(C200)| 成 功 标 志
     *      如果文件标识为1 则此字段不存在 数据保存在文件里
     */
    @JacksonXmlProperty(localName = "serial_record")
    private String serialRecord;

    /**
     * 字段数
     */
    @JacksonXmlProperty(localName = "field_num")
    private Integer fieldNum;

    /**
     * 记录数
     */
    @JacksonXmlProperty(localName = "record_num")
    private Integer recordNum;

    /**
     * 文件标识 1：文件 0：数据包 (如直连机未配置 该字段不存在)
     */
    @JacksonXmlProperty(localName = "file_flag")
    private String fileFlag;

    /**
     * 文件名
     */
    private String filename;

}
