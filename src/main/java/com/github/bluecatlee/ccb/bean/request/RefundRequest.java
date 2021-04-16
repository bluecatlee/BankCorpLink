package com.github.bluecatlee.ccb.bean.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.ccb.bean.CCBRequestBody;
import lombok.Data;

/**
 * 退款请求
 *
 * @Date 2021/2/25 13:44
 */
@Data
public class RefundRequest extends CCBRequestBody {

    /**
     * 退款金额  必填
     */
    @JacksonXmlProperty(localName = "MONEY")
    private String money;

    /**
     * 订单号  必填
     */
    @JacksonXmlProperty(localName = "ORDER")
    private String order;

    /**
     * 退款流水号  非必填
     */
    @JacksonXmlProperty(localName = "REFUND_CODE")
    private String refundCode;

    /**
     * 签名  非必填
     */
    @JacksonXmlProperty(localName = "SIGN_INFO")
    private String signInfo;

    /**
     * 签名CA信息  非必填 (客户采用socket连接时，建行客户端自动添加)
     */
    @JacksonXmlProperty(localName = "SIGNCERT")
    private String signCert;

    @Override
    public String getTxCode() {
        return "5W1004";
    }

}
