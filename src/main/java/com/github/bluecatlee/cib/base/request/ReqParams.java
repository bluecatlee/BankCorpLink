package com.github.bluecatlee.cib.base.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.bluecatlee.cib.base.RespBizAware;
import lombok.Data;

@Data
public abstract class ReqParams implements RespBizAware {

    /**
     * 获取业务标签名
     * @return
     */
    @JsonIgnore
    abstract public String getBizTag();

    /**
     * 获取参数包装标签名 默认RQBODY 子类可重写
     * @return
     */
    @JsonIgnore
    public String getParamsWrapperTag() {
        return "RQBODY";
    }

}
