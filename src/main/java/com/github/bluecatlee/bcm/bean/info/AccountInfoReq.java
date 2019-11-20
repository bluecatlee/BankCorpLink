package com.github.bluecatlee.bcm.bean.info;

import com.github.bluecatlee.bcm.annotation.BcmField;
import com.github.bluecatlee.bcm.request.BcmReqParam;
import lombok.Data;

/**
 * 账户信息查询（310101）
 */
@Data
public class AccountInfoReq extends BcmReqParam {

    @BcmField(required = true, slen = 40)
    private String acno;

    @Override
    public String getTrCode() {
        return "310101";
    }

}
