package com.github.bluecatlee.ccb.bean.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.ccb.bean.CCBRequestBody;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付流水查询请求
 *
 * @Date 2021/2/24 13:58
 */
@Data
public class PayQueryRequest extends CCBRequestBody {

    /**
     * 起始日期
     */
    @JacksonXmlProperty(localName = "START")
    private String start;

    /**
     * 开始小时
     */
    @JacksonXmlProperty(localName = "STARTHOUR")
    private String startHour;

    /**
     * 开始小时
     */
    @JacksonXmlProperty(localName = "STARTMIN")
    private String startMin;

    /**
     * 截止日期
     */
    @JacksonXmlProperty(localName = "END")
    private String end;

    /**
     * 结束小时
     */
    @JacksonXmlProperty(localName = "ENDHOUR")
    private String endHour;

    /**
     * 结束分钟
     */
    @JacksonXmlProperty(localName = "ENDMIN")
    private String endMin;

    /**
     * 流水类型  必填   0:未结流水,1:已结流水
     */
    @JacksonXmlProperty(localName = "KIND")
    private String kind;

    /**
     * 订单号   必填   按订单号查询时，时间段不起作用
     */
    @JacksonXmlProperty(localName = "ORDER")
    private String order;

    /**
     * 结算账户号    暂不用
     */
    @JacksonXmlProperty(localName = "ACCOUNT")
    private String account;

    /**
     * 文件类型   必填  默认为“1”，1:不压缩,2.压缩成zip文件
     */
    @JacksonXmlProperty(localName = "DEXCEL")
    private String dExcel = "1";

    /**
     * 金额       不支持以金额查询
     */
    @JacksonXmlProperty(localName = "MONEY")
    private BigDecimal money;

    /**
     * 排序  必填     1:交易日期,2:订单号
     */
    @JacksonXmlProperty(localName = "NORDERBY")
    private String nOrderBy;

    /**
     * 当前页次  必填
     */
    @JacksonXmlProperty(localName = "PAGE")
    private String page;

    /**
     * 柜台号
     */
    @JacksonXmlProperty(localName = "POS_CODE")
    private String posCode;

    /**
     * 流水状态  必填   0:交易失败,1:交易成功,2:待银行确认(针对未结流水查询);3:全部
     */
    @JacksonXmlProperty(localName = "STATUS")
    private String status;

    /**
     * 子商户号     集团商户查询子商户流水时有效
     */
    @JacksonXmlProperty(localName = "Mrch_No")
    private String mrchNo;

    @Override
    public String getTxCode() {
        return "5W1002";
    }
}
