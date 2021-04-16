package com.github.bluecatlee.ccb.redundance;

import lombok.Data;

@Data
public class BaseResponse {

    /****************************接口返回部分*****************/
    // 本方流水
    private String id;
    // 系统返回码 一般和第三方返回码一致
    private String code;
    // 系统返回状态 一般和第三方返回码一致
    private String message;
    // 订单号
    private String outTradeNo;
    // 原订单号
    private String srcOutTradeNo;
    // 门店号
    private Long subUnitNumId;
    // 支付方式
    private int platType;
    // 请求编号
    private String requestNo;
    // 产生金额
    private Double totalFee;
    // 第三方支付条码号
    private String tranId;
    // 交易日期
    private String txndate;
    // 交易时间
    private String txntime;
    /****************************接口返回部分*****************/

    // 交易类型 1 支付 2 退款
    private Byte tradeType;
    // 商户号
    private Long merchantId;
    // 第三方支付支付流水号
    private String transactionId;
    // 第三方支付平台返回结果
    private String tradeStatus;
    // 第三方支付平台返回结果
    private String tradeStatusRes;

    private String ext1 = "0";
    private String ext2;
    private String ext3;
    private String ext4;

    private String resBody;

    public void clearReturn() {
        this.transactionId = null;
    //    this.tradeStatus = null;
        this.tradeStatusRes = null;
        this.merchantId = null;
        this.tradeType = null;
    }
}
