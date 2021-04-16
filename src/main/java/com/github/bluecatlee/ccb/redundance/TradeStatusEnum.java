package com.github.bluecatlee.ccb.redundance;

public enum TradeStatusEnum {
    SUCCESS("1", "成功"),
    TIMEOUT("2","超时"),
    PROCESSING("11","处理中"),
    FAIL("80","处理失败"),
    SYSTEMFAIL("90","系统处理失败"),
    ERROR("99","系统错误");

    private final String status;
    private final String message;

    TradeStatusEnum(String status, String message) {
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
