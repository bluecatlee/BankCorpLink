package com.github.bluecatlee.ccb.redundance;

public enum RespEnum {
    SUCCESS_EVER("001", "已经成功过了,幂等返回"),
    SUCCESS("00", "成功"),
    SIGNERROR("400","验签错误"),
    ERROR_21("21","请求参数为空"),
    ERROR_22("22","无此支付方式"),
    ERROR_30("30","请求参数验证失败"),
    ERROR_40("40","该订单存在正在支付的数据"),
    ERROR_41("41","该订单存在正在退款的数据"),
    ERROR_42("42","原始付款数据不存在"),
    ERROR_70("70","同一订单只能支付成功一次"),
    ERROR_71("71","同一订单存在支付未完成数据"),
    ERROR_80("80","店铺支付厂家渠道未配置"),
    ERROR_99("99","业务处理失败"),
    ERROR_991("991","微信通知类型错误"),
    ERROR_992("992","业务回调失败"),
    ERROR_993("993","微信退款错误"),
    ERROR_994("994","微信退款不处理90渠道的订单"),
    ERROR_995("995","业务回调，错误的回调类型，不是支付回调，也不是退款回调"),
    ERROR_MINUS_99("-99","请求失败"),
    ERROR_NO_APPKEY_FOUND("-500","未找到当前门店可以使用的索迪斯配置"),
    ERROR_MINUS_100("-100","系统错误"),
    ERROR_100("100","京东通知类型错误"),

    FAIL("-401", "未知原因错误");




    private final String status;
    private final String message;

    RespEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return message;
    }
}
