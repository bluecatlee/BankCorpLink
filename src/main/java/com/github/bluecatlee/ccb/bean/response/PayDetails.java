package com.github.bluecatlee.ccb.bean.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * 支付明细
 *
 * @Date 2021/2/25 14:15
 */
@Data
public class PayDetails {

    /**
     * 交易日期
     */
    @JacksonXmlProperty(localName = "TRAN_DATE")
    private String tranDate;

    /**
     * 记账日期
     */
    @JacksonXmlProperty(localName = "ACC_DATE")
    private String accDate;

    /**
     * 订单号
     */
    @JacksonXmlProperty(localName = "ORDER")
    private String order;

    /**
     * 付款方账号
     */
    @JacksonXmlProperty(localName = "ACCOUNT")
    private String account;

    /**
     * 支付金额
     */
    @JacksonXmlProperty(localName = "PAYMENT_MONEY")
    private String paymentMoney;

    /**
     * 退款金额
     */
    @JacksonXmlProperty(localName = "REFUND_MONEY")
    private String refundMoney;

    /**
     * 柜台号
     */
    @JacksonXmlProperty(localName = "POS_ID")
    private String posId;

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
     * 订单状态     0:失败,1:成功,2:待银行确认,3:已部分退款,4:已全额退款,5:待银行确认
     */
    @JacksonXmlProperty(localName = "ORDER_STATUS")
    private String orderStatus;

    /**
     * 支付方式     BHK:建行;THK:他行;ZFB:支付宝;CFT:微信
     */
    @JacksonXmlProperty(localName = "PAY_MODE")
    private String payMode;

    /**
     * 订单金额     优惠前订单金额（订单金额-支付金额=优惠金额）
     */
    @JacksonXmlProperty(localName = "Orig_Amt")
    private String origAmt;

    /**
     * 结算金额
     */
    @JacksonXmlProperty(localName = "Txn_ClrgAmt")
    private String txnClrgAmt;

    /**
     * 手续费金额
     */
    @JacksonXmlProperty(localName = "MrchCmsn_Amt")
    private String mrchCmsnAmt;

    /**
     * 优惠金额
     */
    @JacksonXmlProperty(localName = "Discount_Amt")
    private String discountAmt;

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
     * 信用卡分期期数
     */
    @JacksonXmlProperty(localName = "CrCrd_Instm_Prd_Num")
    private String crCrdInstmPrdNum;

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

    /**
     * 交易标志位图
     */
    @JacksonXmlProperty(localName = "OnlnPcsgInd_1_Bmp_ECD")
    private String onlnPcsgInd1BmpECD;

    /**
     * 交易金额     与OnlnPcsgInd_1_Bmp_ECD一起使用，为真实扣卡金额
     */
    @JacksonXmlProperty(localName = "TxnAmt")
    private String txnAmt;

    /**
     * 客户类型优惠描述
     */
    @JacksonXmlProperty(localName = "Cst_Tp_Prft_Dsc")
    private String cstTpPrftDsc;

}
