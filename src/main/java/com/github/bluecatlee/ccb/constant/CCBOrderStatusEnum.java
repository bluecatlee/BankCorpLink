package com.github.bluecatlee.ccb.constant;

/**
 * 订单状态
 */
public enum CCBOrderStatusEnum {

    FAIL("0", "失败"),
    SUCCESS("1", "成功"),
    TBC("2", "待银行确认"),
    REBATE("3", "已部分退款"),
    FULL_REDUND("4", "已全额退款"),
    TBC2("5", "待银行确认"),

    ;

    private final String status;
    private final String message;

    CCBOrderStatusEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
