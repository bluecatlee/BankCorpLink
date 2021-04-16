package com.github.bluecatlee.ccb.constant;

/**
 * 流水状态
 *
 * @Date 2021/3/4 14:40
 */
public enum CCBBillStatusEnum {

    TRANS_FAIL("0", "交易失败"),
    TRANS_SUCCESS("1", "交易成功"),
    TBC("2", "待银行确认(针对未结流水查询)"),
    ALL("3", "全部"),

    ;

    private final String status;
    private final String message;

    CCBBillStatusEnum(String status, String message) {
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
