package com.github.bluecatlee.ccb.bean.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * 退款明细
 *
 * @Date 2021/2/26 15:30
 */
@Data
public class RefundDetails {

    /**
     * 交易日期
     */
    @JacksonXmlProperty(localName = "TRAN_DATE")
    private String tranDate;

    /**
     * 退款日期
     */
    @JacksonXmlProperty(localName = "REFUND_DATE")
    private String refundDate;

    /**
     * 订单号
     */
    @JacksonXmlProperty(localName = "ORDER_NUMBER")
    private String orderNumber;

    /**
     * 退款账号
     */
    @JacksonXmlProperty(localName = "REFUND_ACCOUNT")
    private String refundAccount;

    /**
     * 支付金额
     */
    @JacksonXmlProperty(localName = "PAY_AMOUNT")
    private String payAmount;

    /**
     * 退款金额
     */
    @JacksonXmlProperty(localName = "REFUNDEMENT_AMOUNT")
    private String refundementAmount;

    /**
     * 柜台号
     */
    @JacksonXmlProperty(localName = "POS_CODE")
    private String posCode;

    /**
     * 操作员
     */
    @JacksonXmlProperty(localName = "USERID")
    private String userId;

    /**
     * 订单状态         0:失败,1:成功,2:待银行确认,5:待银行确认
     */
    @JacksonXmlProperty(localName = "STATUS")
    private String status;

    /**
     * 退款流水号        商户退款时上送的退款流水号，无上送则不展示
     */
    @JacksonXmlProperty(localName = "REFUND_CODE")
    private String refundCode;

    /**
     * 备注1
     */
    @JacksonXmlProperty(localName = "REM1")
    private String rem1;

    /**
     * 备注2
     */
    @JacksonXmlProperty(localName = "REM2")
    private String rem2;

    /**
     * 支付方式         BHK:建行;THK:他行;ZFB:支付宝;CFT:微信
     */
    @JacksonXmlProperty(localName = "PAY_MODE")
    private String payMode;

    /**
     * 订单金额         优惠前订单金额（订单金额-支付金额=优惠金额）
     */
    @JacksonXmlProperty(localName = "Orig_Amt")
    private String origAmt;

    /**
     * 退款结算金额
     */
    @JacksonXmlProperty(localName = "Txn_ClrgAmt")
    private String txnClrgAmt;

    /**
     * 退款手续费金额
     */
    @JacksonXmlProperty(localName = "MrchCmsn_Amt")
    private String mrchCmsnAmt;

    /**
     * 银行流水号
     */
    @JacksonXmlProperty(localName = "OriOvrlsttnEV_Trck_No")
    private String oriOvrlsttnEVTrckNo;

    /**
     * 商户流水号
     */
    @JacksonXmlProperty(localName = "MsgRp_Jrnl_No")
    private String msgRpJrnlNo;

    /**
     * 卡属性位图
     */
    @JacksonXmlProperty(localName = "Crd_Attr_Bmp_Def_ID")
    private String crdAttrBmpDefId;

    /**
     * 发卡行机构号
     */
    @JacksonXmlProperty(localName = "DstCrd_IssuBnk_InsNo")
    private String dstCrdIssuBnkInsNo;

}
