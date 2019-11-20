package com.github.bluecatlee.bcm.bean.collection;

import com.github.bluecatlee.bcm.annotation.Mfs;
import lombok.Data;

/**
 * 批量代收查询明细
 */
@Data
public class BatchCollecDetail {

    // 网银流水号（C28|付款签约编号(C30)|付款账号(C40)|付款户名(C60)|缴费户名(C60)|收款 方式(C1)|自定义字段值(C200)|收款金额(N14.2)|备注(C60)|状态(C1)|错误信息(C100)|

    /**
     * 网银流水号
     */
    @Mfs(order = 0)
    private String flwno;

    /**
     * 付款签约编号
     */
    @Mfs(order = 1)
    private String subAgrNo;

    /**
     * 付款账号
     */
    @Mfs(order = 2)
    private String payAccNo;

    /**
     * 付款户名
     */
    @Mfs(order = 3)
    private String payAccName;

    /**
     * 缴费户名
     */
    @Mfs(order = 4)
    private String feeName;

    /**
     * 收款方式 0-代扣 1-代缴
     */
    @Mfs(order = 5)
    private String chgmod;

    /**
     * 自子定义字段值
     */
    @Mfs(order = 6)
    private String usrdefineValue;

    /**
     * 收款金额
     */
    @Mfs(order = 7)
    private String tranAmt;

    /**
     * 备注
     */
    @Mfs(order = 8)
    private String rem;

    /**
     * 状态 1-未付款、2-已付款、3-已撤销、4-发生退款、5、付款失败、6、已失效、9-可疑
     */
    @Mfs(order = 9)
    private String status;

    /**
     * 错误信息
     */
    @Mfs(order = 10)
    private String errorMsg;
}
