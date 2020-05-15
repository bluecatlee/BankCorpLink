package com.github.bluecatlee.cib.bean;

import lombok.Data;

@Data
public class TransferResult {

    /**
     * 交易号
     */
    private String transId;

    /**
     * 本次请求发起方需要
     */
    private String sequence;

    /**
     * 是否是中间状态
     */
    private Boolean midStatus;

    /**
     * 状态 FAIL/PAYOUT/PENDING
     */
    private String status;

    private String message;

}
