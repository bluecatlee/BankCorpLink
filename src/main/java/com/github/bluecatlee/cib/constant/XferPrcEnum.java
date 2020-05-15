package com.github.bluecatlee.cib.constant;

public enum XferPrcEnum {

    /**
     * 指令审核中
     */
    AUDITING(0),

    /**
     * 指令已审核
     */
    AUDITED(0),

    /**
     * 指令已撤销
     */
    CANCEL(1),

    /**
     * 指令过期
     */
    EXPIRED(1),

    /**
     * 交易失败
     */
    FAIL(1),

    /**
     * 交易成功/登记成功
     */
    PAYOUT(1),

    /**
     * 指令状态未知
     * 请等待15分钟后再次发起查询，与银行系统进行对账
     */
    PENDING(0),

    /**
     * 部分支付成功
     */
    PART_PAYOUT(1),

    /**
     * 等待银行端审核
     */
    WAIT_FOR_AUDIT(0),

    /**
     * 后台办理中
     */
    PROCESSING(0),

    /**
     * 退回经办
     */
    SEND_BACK(0),

    ;

    /**
     * 0：中间状态 1：最终状态
     */
    private int value;

    XferPrcEnum(int value) {
        this.value = value;
    }

    // public static XferPrcEnum valueOf(int value) {
    //     for (XferPrcEnum xferPrcEnum : XferPrcEnum.values()) {
    //         if (xferPrcEnum.value == value) {
    //             return xferPrcEnum;
    //         }
    //     }
    //     return null;
    // }

}
