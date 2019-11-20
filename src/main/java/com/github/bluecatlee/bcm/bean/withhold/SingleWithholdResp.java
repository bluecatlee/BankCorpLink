package com.github.bluecatlee.bcm.bean.withhold;

import lombok.Data;

@Data
public class SingleWithholdResp {

    /**
     * 状态
     *  0：交易成功 1：交易失败 2：可疑
     */
    private String statu;

    /**
     * 错误代码
     */
    private String errCode;

    /**
     * 错误信息
     */
    private String errMsg;
}
