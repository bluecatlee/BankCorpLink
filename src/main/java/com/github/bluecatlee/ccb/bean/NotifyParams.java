package com.github.bluecatlee.ccb.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 建行支付异步回调通知参数
 *
 * @Date 2021/2/26 10:36
 */
@Data
public class NotifyParams {

    /**
     * 商户柜台代码   从商户传送的信息中获得
     */
    @JsonProperty("POSID")
    @NotEmpty(message = "POSID不能为空")
    private String posId;

    /**
     * 分行代码     从商户传送的信息中获得
     */
    @JsonProperty("BRANCHID")
    @NotEmpty(message = "BRANCHID不能为空")
    private String branchId;

    /**
     * 订单号      从商户传送的信息中获得
     */
    @JsonProperty("ORDERID")
    @NotEmpty(message = "ORDERID不能为空")
    private String orderId;

    /**
     * 付款金额     从商户传送的信息中获得
     */
    @JsonProperty("PAYMENT")
    @NotEmpty(message = "PAYMENT不能为空")
    private String payment;

    /**
     * 币种       从商户传送的信息中获得
     */
    @JsonProperty("CURCODE")
    @NotEmpty(message = "CURCODE不能为空")
    private String curCode;

    /**
     * 备注1      从商户传送的信息中获得
     */
    @JsonProperty("REMARK1")
    @NotNull(message = "REMARK1不能为空")
    private String remark1;

    /**
     * 备注2      从商户传送的信息中获得
     */
    @JsonProperty("REMARK2")
    @NotNull(message = "REMARK2不能为空")
    private String remark2;

    /**
     * 账户类型
     *      AL:代表支付宝支付
     *      WX:代表微信支付
     *      其他：代表建行支付或跨行付
     */
    @JsonProperty("ACC_TYPE")
//    @NotEmpty(message = "ACC_TYPE不能为空")
    private String accType;

    /**
     * 成功标志     成功－Y，失败－N
     */
    @JsonProperty("SUCCESS")
    @NotEmpty(message = "SUCCESS不能为空")
    private String success;

    /**
     * 接口类型     分行业务人员在P2员工渠道后台设置防钓鱼的开关
     *      1.开关关闭时，无此字段返回且不参与验签。
     *      2.开关打开时，有此字段返回且参与验签。
     */
    @JsonProperty("TYPE")
    private String type;

    /**
     * Referer信息    分行业务人员在P2员工渠道后台设置防钓鱼开关。
     *      1.开关关闭时，无此字段返回且不参与验签。
     *      2.开关打开时，有此字段返回且参与验签。
     */
    @JsonProperty("REFERER")
    private String referer;

    /**
     * 客户端IP        客户在商户系统中的IP，即客户登陆（访问）商户系统时使用的ip）
     */
    @JsonProperty("CLIENTIP")
    private String clientip;

    /**
     * 系统记账日期       商户登陆商户后台设置返回记账日期的开关
     *       1.开关关闭时，无此字段返回且不参与验签。
     *       2.开关打开时，有此字段返回且参与验签。参数值格式为YYYYMMDD（如20100907）。
     */
    @JsonProperty("ACCDATE")
    private String accDate;

    /**
     * 分期期数     从商户传送的信息中获得;
     *      当分期期数为空或无此字段上送时，无此字段返回且不参与验签，否则有此字段返回且参与验签。
     */
    @JsonProperty("INSTALLNUM")
    private String installNum;

    /**
     * 错误信息     该值默认返回为空，商户无需处理，仅需参与验签即可。当有分期期数返回时，则有ERRMSG字段返回且参与验签，否则无此字段返回且不参与验签
     */
    @JsonProperty("ERRMSG")
    private String errMsg;

    /**
     * 支付账户信息       分行业务人员在P2员工渠道后台设置防钓鱼开关和返回账户信息的开关。
     *      1.开关关闭时，无此字段返回且不参与验签。
     *      2.开关打开但支付失败时，无此字段返回且不参与验签。
     *      3.开关打开且支付成功时，有此字段返回且参与验签。参数值格式如下：“姓名|账号加密后的密文”。
     */
    @JsonProperty("USRMSG")
    private String usrMsg;

    /**
     * 用户加密信息   分行业务人员在P2员工渠道后台设置防钓鱼开关和客户信息加密返回的开关。
     *      1.开关关闭时，无此字段返回且不参与验签
     *      2.开关打开时，有此字段返回且参数验签。参数值格式如下：“证件号密文|手机号密文”。该字段不可解密。
     */
    @JsonProperty("USRINFO")
    private String usrInfo;

    /**
     * 返回客户的实际支付金额
     *      目前只针对白名单商户返回，无此字段返回且不参与验签，有此字段返回且参与验签。
     */
    @JsonProperty("DISCOUNT")
    private String discount;

    /**
     * 返回客户的积分使用情况
     *      当综合积分字段为空或无此字段上送时，无此字段返回且不参与验签，否则有此字段返回且参与验签。
     */
    @JsonProperty("ZHJF")
    private String zhJf;

    /**
     * 客户识别号
     *      提交建行的参数RETURN_FIELD打开对应开关才返回该字段。微信、支付宝、龙支付时返回。
     */
    @JsonProperty("OPENID")
    private String openid;

    /**
     * 客户子标识
     *      提交建行的参数RETURN_FIELD打开对应开关才返回该字段。微信支付专有字段。子商户appid下用户唯一标识，如需返回则请求时需要传sub_appid。
     */
    @JsonProperty("SUB_OPENID")
    private String subOpenid;

    /**
     * 支付详细信息
     */
    @JsonProperty("PAYMENT_DETAILS")
    private String paymentDetails;

    /**
     * 数字签名
     */
    @JsonProperty("SIGN")
    @NotEmpty(message = "SIGN不能为空")
    private String sign;

    // ------------------------------------------------------------------

    // 解密后的用户信息
    private String decodedUsrMsg;

    private PaymentDetail _paymentDetail;

    @Data
    public class PaymentDetail {

        /**
         * 支付方式
         *      支付宝：ALIPAY
         *      微信：WEIXIN
         *      建行：CCB
         *      银联：UNIONPAY（暂不支持）
         */
        @JsonProperty("TYPE")
        private String type;

        /**
         * 支付渠道
         */
        @JsonProperty("PAY_CHANNEL")
        private String payChannel;

        /**
         * 借贷记标识
         *      借记：DEBIT
         *      贷记：CREDIT
         *      当支付方式为银联时，该字段返回为空
         */
        @JsonProperty("DEBIT_CREDIT_TYPE")
        private String debitCreditType;

        /**
         * 第三方订单号
         *      返回支付宝/微信订单号
         *      当支付方式为龙支付或银联时，该字段返回为空
         */
        @JsonProperty("THIRD_TRADE_NO")
        private String thirdTradeNo;

    }

}
