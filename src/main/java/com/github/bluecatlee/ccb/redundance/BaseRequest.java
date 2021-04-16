package com.github.bluecatlee.ccb.redundance;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
@Accessors(chain = true)
public class BaseRequest {

    // 订单号
    @NotBlank
    private String outTradeNo;
    // 原订单号
    private String srcOutTradeNo;
    // 门店号
    @NotNull
    private String subUnitNumId;
    // 商户id
    private String merchantId;
    // 支付方式
    @NotNull
    private String platType;
    // 请求编号
    @NotBlank
    private String requestNo;
    // 第三方支付支付流水号
    private String transactionId;
    // 支付类型 1 支付 2 退款
    private String tradeType;
    // 产生金额
    private String totalFee;
    // 订单金额
    private String orderAmount;
    // 设备
    private String deviceInfo;
    // 订单描述
    private String itemBody;
    // 会员id
    private String openid;
    //
    private String attach;
    // 订单标题
    private String subject;
    // ip
    private String createIp;
    // 第三方支付平台返回结果
    private String tradeStatusRes;
    // 来源渠道
    @NotBlank
    private String channel;
    // 第三方支付条码号
    private String tranId;
    // 终端号
    private String terminal;
    // 订单日期
    private String ordeDate;
    // 交易日期
    private String txndate;
    // 交易时间
    private String txntime;
    // 原订单号
    private PayOrderInfo srcPayOrderInfo;
    // 订单号
    private String usrNumId;

    /**
     * 下面两个参数用于通用服务对请求不能解析的情况.比如返回的加密信息,必须路由到最底层才能解析出参数
     */
    /**
     * 请求body
     */
    private String body;

    /**
     * 请求参数,非body部分
     */
    private Map requestParam;

    /**
     * 业务回调, 可能是带参url, 也可能是dubbo方法
     */
    private String callback;

}
