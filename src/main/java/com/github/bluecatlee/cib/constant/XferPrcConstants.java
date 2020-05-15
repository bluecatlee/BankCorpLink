package com.github.bluecatlee.cib.constant;

/**
 * 交易指令处理状态
 */
@Deprecated
public class XferPrcConstants {

    /**
     * 指令审核中 中间值
     */
    public static final String AUDITING = "AUDITING";

    /**
     * 指令已审核 中间值
     */
    public static final String AUDITED = "AUDITED";

    /**
     * 指令已撤销 最终值
     */
    public static final String CANCEL = "CANCEL";

    /**
     * 指令过期 最终值
     */
    public static final String EXPIRED = "EXPIRED";

    /**
     * 交易失败 最终值
     */
    public static final String FAIL = "FAIL";

    /**
     * 交易成功/登记成功 最终值
     */
    public static final String PAYOUT = "PAYOUT";

    /**
     * 指令状态未知 中间值
     * 请等待15分钟后再次发起查询，与银行系统进行对账。
     */
    public static final String PENDING = "PENDING";

    /**
     * 部分支付成功 最终值
     */
    public static final String PART_PAYOUT = "PART_PAYOUT";

    /**
     * 等待银行端审核 中间值
     */
    public static final String WAIT_FOR_AUDIT = "WAIT_FOR_AUDIT";

    /**
     * 后台办理中 中间值
     */
    public static final String PROCESSING = "PROCESSING";

    /**
     * 退回经办 中间值
     */
    public static final String SEND_BACK = "SEND_BACK";

}
