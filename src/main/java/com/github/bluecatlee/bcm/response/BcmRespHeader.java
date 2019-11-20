package com.github.bluecatlee.bcm.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * 公共响应报文头
 */
@Data
public class BcmRespHeader {

    /**
     * 交易码
     */
    @JacksonXmlProperty(localName = "tr_code")
    private String trCode;

    /**
     * 企业代码
     */
    @JacksonXmlProperty(localName = "corp_no")
    private String corpNo;

    /**
     * 发起方序号 原样返回
     */
    @JacksonXmlProperty(localName = "req_no")
    private String reqNo;

    /**
     * 交易序号 银行系统流水号
     */
    @JacksonXmlProperty(localName = "serial_no")
    private String serialNo;

    /**
     * 应答流水号 备用
     */
    @JacksonXmlProperty(localName = "ans_no")
    private String ansNo;

    /**
     * 下笔交易序号 备用
     */
    @JacksonXmlProperty(localName = "next_no")
    private String nextNo;

    /**
     * 交易日期
     */
    @JacksonXmlProperty(localName = "tr_acdt")
    private String trAcdt;

    /**
     * 时间
     */
    @JacksonXmlProperty(localName = "tr_time")
    private String trTime;

    /**
     * 返回码 对于直连交易返回码均为0
     */
    @JacksonXmlProperty(localName = "ans_code")
    private String ansCode;

    /**
     * 返回信息 交易成功返回空格 交易异常返回CS端信息
     */
    @JacksonXmlProperty(localName = "ans_info")
    private String ansInfo;

    /**
     * 返回附加码(4位) 0000：受理成功 【交易结果标识】
     */
    @JacksonXmlProperty(localName = "particular_code")
    private String particularCode;

    /**
     * 返回附加信息 受理信息或错误信息
     */
    @JacksonXmlProperty(localName = "particular_info")
    private String particularInfo;

    /**
     * 原子交易数
     */
    @JacksonXmlProperty(localName = "atom_tr_count")
    private String atomTrCount;

    /**
     * 保留字段
     */
    private String reserved;

}


