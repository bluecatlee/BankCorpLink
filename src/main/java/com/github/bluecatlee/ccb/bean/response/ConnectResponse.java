package com.github.bluecatlee.ccb.bean.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.ccb.bean.CCBResponseBody;
import lombok.Data;

/**
 * 商户连接交易响应
 *
 * @Date 2021/2/24 14:14
 */
@Data
public class ConnectResponse extends CCBResponseBody {

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

}
