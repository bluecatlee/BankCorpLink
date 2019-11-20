package com.github.bluecatlee.bcm.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 公共请求报文头
 */
@Data
// @JacksonXmlRootElement(localName = "head")
public class BcmReqHeader {

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
     * 企业用户号
     */
    @JacksonXmlProperty(localName = "user_no")
    private String userNo;

    /**
     * 发起方序号
     *  企业方产生
     */
    @JacksonXmlProperty(localName = "req_no")
    private String reqNo;

    /**
     * 交易日期
     * 日期字符串 如20061206
     */
    @JacksonXmlProperty(localName = "tr_acdt")
    private String trAcdt =  new SimpleDateFormat("yyyyMMdd").format(new Date());

    /**
     * 时间
     * 时间字符串 如100108
     */
    @JacksonXmlProperty(localName = "tr_time")
    private String trTime = new SimpleDateFormat("HHmmss").format(new Date());

    /**
     * 原子交易数
     *  原子交易数缺省填1，如果查询多账户信息，请求报文中原子交易数根据账户数来填写
     */
    @JacksonXmlProperty(localName = "atom_tr_count")
    private String atomTrCount = "1";

    /**
     * 渠道标志
     */
    private String channel = "0";

    /**
     * 保留字段
     */
    private String reserved;
}
