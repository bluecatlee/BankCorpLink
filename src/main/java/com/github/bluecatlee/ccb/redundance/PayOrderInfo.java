package com.github.bluecatlee.ccb.redundance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(name = "pay_order_info")
public class PayOrderInfo {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long series;

    /**
     * 租户号
     */
    @Column(name = "tenant_num_id")
    private Long tenantNumId;

    /**
     * 商户号
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 门店号
     */
    @Column(name = "sub_unit_num_id")
    private Long subUnitNumId;

    /**
     * 支付渠道: 1微信 2支付宝 3预付卡
     */
    @Column(name = "plat_type")
    private Integer platType;

    /**
     * 订单号(包括消费和退货)
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 请求编号防重
     */
    @Column(name = "request_no")
    private String requestNo;

    /**
     * 第三方支付订单流水号
     */
    @Column(name = "transaction_id")
    private String transactionId;

    /**
     * 发生金额
     */
    @Column(name = "total_fee")
    private Double totalFee;

    /**
     * 订单状态 1：成功 2 : 超时  11：支付中 5：撤销成功 6：全部退款成功 7：部分退款 9：预付款成功 10：退款失败 -97：支付错误 -98：未支付 -99：未知错误 -100：订单不存在
     */
    @Column(name = "trade_status")
    private String tradeStatus;

    /**
     * 1 支付 2 退款
     */
    @Column(name = "trade_type")
    private Byte tradeType;

    /**
     * 设备号
     */
    @Column(name = "device_info")
    private String deviceInfo;

    @Column(name = "close_sign")
    private Byte closeSign;

    /**
     * 请求时间
     */
    @Column(name = "create_dtme")
    private Date createDtme;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "create_user_id")
    private Long createUserId;

    /**
     * 0：正式  1：测试
     */
    @Column(name = "data_sign")
    private Byte dataSign;

    /**
     * 订单描述
     */
    @Column(name = "item_body")
    private String itemBody;

    private String openid;

    private String attach;

    @Column(name = "cancel_sign")
    private Byte cancelSign;
    
    @Column(name = "business_callback_status")
    private Byte businessCallbackStatus;
    
    @Column(name = "business_callback")
    private String businessCallback;

    /**
     * 订单标题
     */
    private String subject;

    @Column(name = "pay_app_key")
    private String payAppKey;

    @Column(name = "create_ip")
    private String createIp;

    /**
     * 第三方支付平台返回结果
     */
    @Column(name = "trade_status_res")
    private String tradeStatusRes;

    /**
     * 来源渠道 
     */
    private String channel;

    /**
     * 交易日期 格式:YYMMDD
     */
    private String txndate;

    /**
     * 支付产生的时间 格式:HHMMSS
     */
    private String txntime;

    /**
     * 第三方支付条码号
     */
    @Column(name = "tranId")
    private String tranid;

    /**
     * 备用1
     */
    private String ext1;

    /**
     * 备用2
     */
    private String ext2;

    /**
     * 备用3
     */
    private String ext3;

    /**
     * 备用4
     */
    private String ext4;

    /**
     * 原订单号(退货)
     */
    @Column(name = "src_out_trade_no")
    private String srcOutTradeNo;

    public static final String SERIES = "series";

    public static final String DB_SERIES = "series";

    public static final String TENANT_NUM_ID = "tenantNumId";

    public static final String DB_TENANT_NUM_ID = "tenant_num_id";

    public static final String MERCHANT_ID = "merchantId";

    public static final String DB_MERCHANT_ID = "merchant_id";

    public static final String SUB_UNIT_NUM_ID = "subUnitNumId";

    public static final String DB_SUB_UNIT_NUM_ID = "sub_unit_num_id";

    public static final String PLAT_TYPE = "platType";

    public static final String DB_PLAT_TYPE = "plat_type";

    public static final String OUT_TRADE_NO = "outTradeNo";

    public static final String DB_OUT_TRADE_NO = "out_trade_no";

    public static final String REQUEST_NO = "requestNo";

    public static final String DB_REQUEST_NO = "request_no";

    public static final String TRANSACTION_ID = "transactionId";

    public static final String DB_TRANSACTION_ID = "transaction_id";

    public static final String TOTAL_FEE = "totalFee";

    public static final String DB_TOTAL_FEE = "total_fee";

    public static final String TRADE_STATUS = "tradeStatus";

    public static final String DB_TRADE_STATUS = "trade_status";

    public static final String TRADE_TYPE = "tradeType";

    public static final String DB_TRADE_TYPE = "trade_type";

    public static final String DEVICE_INFO = "deviceInfo";

    public static final String DB_DEVICE_INFO = "device_info";

    public static final String CLOSE_SIGN = "closeSign";

    public static final String DB_CLOSE_SIGN = "close_sign";

    public static final String CREATE_DTME = "createDtme";

    public static final String DB_CREATE_DTME = "create_dtme";

    public static final String UPDATE_TIME = "updateTime";

    public static final String DB_UPDATE_TIME = "update_time";

    public static final String CREATE_USER_ID = "createUserId";

    public static final String DB_CREATE_USER_ID = "create_user_id";

    public static final String DATA_SIGN = "dataSign";

    public static final String DB_DATA_SIGN = "data_sign";

    public static final String ITEM_BODY = "itemBody";

    public static final String DB_ITEM_BODY = "item_body";

    public static final String OPENID = "openid";

    public static final String DB_OPENID = "openid";

    public static final String ATTACH = "attach";

    public static final String DB_ATTACH = "attach";

    public static final String CANCEL_SIGN = "cancelSign";

    public static final String DB_CANCEL_SIGN = "cancel_sign";

    public static final String SUBJECT = "subject";

    public static final String DB_SUBJECT = "subject";

    public static final String PAY_APP_KEY = "payAppKey";

    public static final String DB_PAY_APP_KEY = "pay_app_key";

    public static final String CREATE_IP = "createIp";

    public static final String DB_CREATE_IP = "create_ip";

    public static final String TRADE_STATUS_RES = "tradeStatusRes";

    public static final String DB_TRADE_STATUS_RES = "trade_status_res";

    public static final String CHANNEL = "channel";

    public static final String DB_CHANNEL = "channel";

    public static final String TXNDATE = "txndate";

    public static final String DB_TXNDATE = "txndate";

    public static final String TXNTIME = "txntime";

    public static final String DB_TXNTIME = "txntime";

    public static final String TRANID = "tranid";

    public static final String DB_TRANID = "tranId";

    public static final String EXT1 = "ext1";

    public static final String DB_EXT1 = "ext1";

    public static final String EXT2 = "ext2";

    public static final String DB_EXT2 = "ext2";

    public static final String EXT3 = "ext3";

    public static final String DB_EXT3 = "ext3";

    public static final String EXT4 = "ext4";

    public static final String DB_EXT4 = "ext4";

    public static final String SRC_OUT_TRADE_NO = "srcOutTradeNo";

    public static final String DB_SRC_OUT_TRADE_NO = "src_out_trade_no";
    
    public static final String BUSINESS_CALLBACK_STATUS = "businessCallbackStatus";
    
    public static final String DB_BUSINESS_CALLBACK_STATUS = "business_callback_status";

    
    public static final String BUSINESS_CALLBACK = "businessCallback";
    
    public static final String DB_BUSINESS_CALLBACK = "business_callback";



}