package com.github.bluecatlee.bcm.bean.collection;

import lombok.Data;

@Data
public class QueryBatchCollecResp {

    /**
     * 网银批次流水号
     */
    private String batchFlwno;

    /**
     * 收款账号
     */
    private String recAccNo;

    /**
     * 业务类型
     */
    private String purpose;

    /**
     * 自定义字段名称
     */
    private String usrdefine;

    /**
     * 多域拼串
     *    网银流水号(C28)|付款签约编号(C30)|付款账号(C40)|付款户名(C60)|缴费户名(C60)|收款 方式(C1)|自定义字段值(C200)|收款金额(N14.2)|备注(C60)|状态(C1)|错误信息(C100)|
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
     * 结束标志 是否包含最后一笔明细 0:已结束 1:未结束
     */
    private String dataEnd;
}
