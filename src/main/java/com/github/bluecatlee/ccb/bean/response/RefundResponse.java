package com.github.bluecatlee.ccb.bean.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.ccb.bean.CCBResponseBody;
import lombok.Data;

/**
 * 退款响应
 *
 * @Date 2021/2/25 13:50
 */
@Data
public class RefundResponse extends CCBResponseBody {

    /**
     * 订单号
     */
    @JacksonXmlProperty(localName = "ORDER_NUM")
    private String orderNum;

    /**
     * 支付金额
     */
    @JacksonXmlProperty(localName = "PAY_AMOUNT")
    private String payAmount;

    /**
     * 退款金额
     */
    @JacksonXmlProperty(localName = "AMOUNT")
    private String amount;

}
