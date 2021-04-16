package com.github.bluecatlee.ccb.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 建行请求体
 *
 * @Date 2021/2/24 13:43
 */
public abstract class CCBRequestBody {

    /**
     * 每种请求都有特定的交易码 子类实现
     */
    @JsonIgnore
    public abstract String getTxCode();

}
