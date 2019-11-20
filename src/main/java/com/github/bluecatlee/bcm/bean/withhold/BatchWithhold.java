package com.github.bluecatlee.bcm.bean.withhold;

import com.github.bluecatlee.bcm.annotation.BcmField;
import com.github.bluecatlee.bcm.request.BcmReqParam;
import lombok.Data;

/**
 * 收富通宝－协议收款－本行批量代扣 210228 (只支持人民币)
 */
@Data
public class BatchWithhold extends BcmReqParam {

    /**
     * 企业凭证号
     */
    @BcmField(required = true, slen = 20)
    private String dealNo;

    /**
     * 收款账号
     *  40位交行账号
     */
    @BcmField(required = true, slen = 40)
    private String recAccNo;

    /**
     * 收款账号户名
     */
    @BcmField(required = true, slen = 60)
    private String recAccName;

    /**
     * 代扣协议编号
     */
    @BcmField(required = true, slen = 40)
    private String contractNo;

    /**
     * 总笔数
     */
    @BcmField(required = true, dlen = 8)
    private String totalNum;

    /**
     * 总金额
     */
    @BcmField(required = true, dlen = 14, plen = 2)
    private String totalAmt;

    /**
     * 摘要
     *  批次表备注
     */
    @BcmField(slen = 60)
    private String summary;

    /**
     * 子序列号 从1开始计数 逐次递增多域串
     */
    @BcmField(slen = 8)
    private String serialno;

    /**
     * 付款账号 多域串
     */
    @BcmField(required = true, slen = 40)
    private String payAccNo;

    /**
     * 付款账号户名 多域串
     */
    @BcmField(required = true, slen = 60)
    private String payAccName;

    /**
     * 收款金额 多域串
     */
    @BcmField(required = true, slen = 14, plen = 2)
    private String tranAmt;

    /**
     * 备注 多域串
     */
    @BcmField(slen = 60)
    private String rem;

    /**
     * 二级商户编号
     *  如果企业性质为第三方支付公司 则必填
     */
    @BcmField(slen = 20)
    private String subMercNo;

    /**
     * 二级商户名称 多域串
     *  如果企业性质为第三方支付公司 则必填
     */
    @BcmField(slen = 60)
    private String subMercName;

    /**
     * 回单打印标识 0：不汇总打印 1：按天汇总打印
     */
    @BcmField(slen = 1)
    private String unitFlg = "0";

    @Override
    public String getTrCode() {
        return "210228";
    }
}
