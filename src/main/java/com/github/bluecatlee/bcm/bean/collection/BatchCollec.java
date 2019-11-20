package com.github.bluecatlee.bcm.bean.collection;

import com.github.bluecatlee.bcm.annotation.BcmField;
import com.github.bluecatlee.bcm.request.BcmReqParam;
import lombok.Builder;
import lombok.Data;

/**
 * 收富通宝－在线签约收款-批量代收（210401）
 */
@Data
@Builder
public class BatchCollec extends BcmReqParam {

    /**
     * 企业凭证号
     */
    @BcmField(required = true, slen = 20)
    private String dealNo;

    /**
     * 收款协议编号
     */
    @BcmField(required = true, slen = 30)
    private String cagrNo;

    /**
     * 收款账号 21位交行账号
     */
    @BcmField(required = true, slen = 40)
    private String recAccNo;

    /**
     * 收款户名
     */
    @BcmField(required = true, slen = 60)
    private String recAccName;

    /**
     * 报文总笔数 最大笔数 500
     */
    @BcmField(required = true, dlen = 8)
    private String totalNum;

    /**
     * 批次总笔数 不超过 5000 一个批次中的各个报文中的企业凭证号应一致
     */
    @BcmField(required = true, dlen = 8)
    private String batchSum;

    /**
     * 偏移量 从0开始  ‘偏移量’+‘总笔数’=‘批次总笔数’时，表示该批量代收提交成功，待银行进行扣款交易
     */
    @BcmField(required = true, dlen = 8)
    private String offset;

    /**
     * 报文总金额 所有报文总金额之和
     */
    @BcmField(required = true, dlen = 14, plen = 2)
    private String totalAmt;

    /**
     * 批次总金额
     */
    @BcmField(required = true, dlen = 14, plen = 2)
    private String batchSumAmt;

    /**
     * 付款签约编号 多域串
     */
    @BcmField(required = true)
    private String subAgrNo;

    /**
     * 付款账号 多域串
     */
    @BcmField(required = true)
    private String payAccNo;

    /**
     * 付款账号户名 多域串
     */
    @BcmField(required = true)
    private String payAccName;

    /**
     * 缴费户名 多域串
     */
    @BcmField(required = true)
    private String feeName;

    /**
     * 收款方式 多域串  0-代扣 1-代缴
     */
    @BcmField(required = true)
    private String chgmod;

    /**
     * 自子定义字段值 多域串
     */
    @BcmField(required = true)
    private String usrdefineValue;

    /**
     * 收款金额 多域串
     */
    @BcmField(required = true)
    private String tranAmt;

    /**
     * 备注 多域串
     */
    @BcmField(required = true)
    private String rem;

    @Override
    public String getTrCode() {
        return "210401";
    }
}
