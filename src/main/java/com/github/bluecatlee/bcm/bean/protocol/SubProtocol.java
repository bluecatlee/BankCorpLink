package com.github.bluecatlee.bcm.bean.protocol;

import com.github.bluecatlee.bcm.annotation.Mfs;
import lombok.Data;

/**
 * 子协议
 */
@Data
public class SubProtocol {

    //付款签约编号(C30)|付款账号(C40)|付款户名(C60)|缴费户名(C60)|自定义字段值 (C200)|单笔限额(C15)|当日累计限额(C15)|
    // 当月累计限额(C15)|是否需要确认后扣款 (C1)|证件类型(C2)|证件号码(30)|协议生效日期(C8)|协议失效日期(C8)|收款方式 (C1)

    /**
     * 付款签约编号
     */
    @Mfs(order = 0)
    private String cagrNo;

    /**
     * 付款账号
     */
    @Mfs(order = 1)
    private String accNo;

    /**
     * 付款户名
     */
    @Mfs(order = 2)
    private String payAcname;

    /**
     * 缴费户名
     */
    @Mfs(order = 3)
    private String feeName;

    /**
     * 自定义字段值
     */
    @Mfs(order = 4)
    private String usrdefine;

    /**
     * 单笔限额
     */
    @Mfs(order = 5)
    private String singleQuota;

    /**
     * 当日累计限额
     */
    @Mfs(order = 6)
    private String dailyCumulativeQuota;

    /**
     * 当月累计限额
     */
    @Mfs(order = 7)
    private String monthlyCumulativeQuota;

    /**
     * 是否需要确认后扣款  Y-是 N-否
     */
    @Mfs(order = 8)
    private String confirmFlag;

    /**
     * 证件类型 居民身份证 15，临时身份证 16，军人身份证 17，武警身份证 18，通信证 19， 护照 20，其他 21，临时户口 22，户口簿 23
     */
    @Mfs(order = 9)
    private String certType;

    /**
     * 证件号码
     */
    @Mfs(order = 10)
    private String certNo;

    /**
     * 协议生效日期
     */
    @Mfs(order = 11)
    private String proEffectTime;

    /**
     * 协议失效日期
     */
    @Mfs(order = 12)
    private String proExpireTime;

    /**
     * 收款方式 ：0-代扣 1-代缴
     */
    @Mfs(order = 13)
    private String collectType;

}
