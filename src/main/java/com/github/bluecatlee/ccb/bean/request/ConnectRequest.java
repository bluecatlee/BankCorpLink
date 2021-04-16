package com.github.bluecatlee.ccb.bean.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.ccb.bean.CCBRequestBody;
import lombok.Data;

/**
 * 商户连接交易请求
 *
 * @Date 2021/2/24 13:30
 */
@Data
public class ConnectRequest extends CCBRequestBody {

    /**
     * 备注1
     */
    @JacksonXmlProperty(localName = "REM1")
    private String rem1;

    /**
     * 备注2
     */
    @JacksonXmlProperty(localName = "REM2")
    private String rem2;

    @Override
    public String getTxCode() {
        return "5W1001";
    }

}
