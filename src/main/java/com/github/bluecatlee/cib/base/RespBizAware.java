package com.github.bluecatlee.cib.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 返回业务标识感知
 */
public interface RespBizAware {

    @JsonIgnore
    String getRespBizTag();

    @JsonIgnore
    default String getResultWrapperTag() {
        return "RSBODY";
    }

}
