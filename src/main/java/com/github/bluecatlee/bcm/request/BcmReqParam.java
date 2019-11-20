package com.github.bluecatlee.bcm.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class BcmReqParam {

    @JsonIgnore
    public abstract String getTrCode();

}
