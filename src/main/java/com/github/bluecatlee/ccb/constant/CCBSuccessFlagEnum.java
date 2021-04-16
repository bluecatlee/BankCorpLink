package com.github.bluecatlee.ccb.constant;

/**
 *  成功标志
 */
public enum CCBSuccessFlagEnum {

    SUCCESS("Y"),
    FAIL("N"),
    ;

    private final String value;

    CCBSuccessFlagEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
