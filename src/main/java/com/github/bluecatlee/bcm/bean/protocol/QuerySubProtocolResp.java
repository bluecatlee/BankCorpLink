package com.github.bluecatlee.bcm.bean.protocol;

import lombok.Data;

@Data
public class QuerySubProtocolResp {

    /**
     * 收款协议编号 原样返回
     */
    private String cagrNo;

    /**
     * 收款账号 40 位交行账号
     */
    private String recAccNo;

    /**
     * 收款户名
     */
    private String rcvname;

    /**
     * 业务类型
     */
    private String purpose;

    /**
     * 自子定义字段
     */
    private String usrdefine;

    /**
     * 多域拼串
     * 付款签约编号(C30)|付款账号(C40)|付款户名(C60)|缴费户名(C60)|自定义字段值 (C200)|单笔限额(C15)|当日累计限额(C15)|
     * 当月累计限额(C15)|是否需要确认后扣款 (C1)|证件类型(C2)|证件号码(30)|协议生效日期(C8)|协议失效日期(C8)|收款方式 (C1)
     *          是否需要确认后扣款 Y：是 N：否
     *          单笔限额、当日累计限额、当月累计限额以分为单位
     *
     */
    private String serialRecord;

    /**
     * 字段数
     */
    private Integer fieldNum;

    /**
     * 返回记录数
     */
    private Integer recordNum;

    /**
     * 结束标识 是否包含最后一笔明细  0:已结束 1:未结束
     */
    private String dataEnd;
}
