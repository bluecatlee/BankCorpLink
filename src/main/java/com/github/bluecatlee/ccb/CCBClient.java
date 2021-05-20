package com.github.bluecatlee.ccb;

import CCBSign.RSASig;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.bluecatlee.ccb.annotation.CCBPayField;
import com.github.bluecatlee.ccb.api.CCBApi;
import com.github.bluecatlee.ccb.utils.Escape;
import com.github.bluecatlee.ccb.utils.MCipherDecode;
import com.github.bluecatlee.ccb.utils.MD5Utils;
import com.github.bluecatlee.ccb.utils.StringUtil;
import com.github.bluecatlee.ccb.bean.*;
import com.github.bluecatlee.ccb.bean.request.ConnectRequest;
import com.github.bluecatlee.ccb.bean.request.PayRequest;
import com.github.bluecatlee.ccb.bean.response.ConnectResponse;
import com.github.bluecatlee.ccb.redundance.MessagePack;
import com.github.bluecatlee.common.id.configuration.IdWorker;
import com.github.bluecatlee.common.id.enumeration.Biz;
import com.github.bluecatlee.common.id.enumeration.PayBizDetail;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import javax.annotation.PostConstruct;
import java.io.*;
import java.lang.reflect.Field;
import java.net.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//import com.github.bluecatlee.ccb.utils.SeqUtil;

/**
 * 建行外联平台客户端调用器
 *
 * @Date 2021/2/23 15:12
 */
@Slf4j
@Component
public class CCBClient implements InitializingBean {

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    private static final String xmlHeader = "<?xml version=\"1.0\" encoding=\"GB2312\" standalone=\"yes\" ?>";
    private static final String TAG = "[CCBClient] 请求建设银行外联客户端, ";

    public static final String CHARSET_GB2312 = "GB2312";
    public static final String CHARSET_GBK = "GBK";    // 兼容GB2312
    public static final String CHARSET_GB18030 = "GB18030";  // 兼容GBK
    public static final String CHARSET_ISO_8859_1 = "ISO-8859-1";
    public static final String CHARSET_UTF_8 = "UTF-8";

    static {
        XML_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        XML_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Getter
    private CCBApi ccbApi;

    @Value("${ccb.url}")
    private String apiUrl;  // 建设银行外联客户端地址
    @Value("${ccb.custId}")
    private String custId;  // 商户号
    @Value("${ccb.posId}")
    private String posId;   // 商户柜台代码
    @Value("${ccb.branchId}")
    private String branchId;   // 分行代码
//    @Value("${ccb.antifish}")
//    private String antifish;   // 防钓鱼开关 1表示开启
    @Value("${ccb.password}")
    private String password;  // 交易密码
    @Value("${ccb.userId}")
    private String userId;  // 操作员号
    @Value("${ccb.publicKey}")
    private String publicKey;   // 商户公钥

    @Autowired
    private IdWorker idWorker;

    @PostConstruct
    public void generateApi() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)               // 请求耗时较大 超时时间要调长一点
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        ccbApi = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(apiUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CCBApi.class);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        Assert.hasLength(apiUrl, "建行外联客户端地址配置不能为空");
//        Assert.hasLength(custId, "建行商户号配置不能为空");
//        Assert.hasLength(userId, "建行外联客户端操作员号配置不能为空");
//        Assert.hasLength(password, "建行外联客户端交易密码配置不能为空");
//        Assert.hasLength(publicKey, "建行支付公钥配置不能为空");
    }

    /**
     * 执行
     *      封装异常信息,上层处理
     * @param body
     * @param typeReference
     * @param <T>
     * @return
     */
    public <T extends CCBResponseBody> CCBBaseResponse<T> executeRequestNew(CCBRequestBody body, TypeReference<CCBBaseResponse<T>> typeReference) {
        return this.executeRequestNew(body, typeReference, true);
    }

    /**
     * 执行
     * @param body
     * @param typeReference
     * @param connect
     * @param <T>
     * @return
     */
    public <T extends CCBResponseBody> CCBBaseResponse<T> executeRequestNew(CCBRequestBody body, TypeReference<CCBBaseResponse<T>> typeReference, boolean connect) {
        log.info(TAG + "请求参数: " + JSONObject.toJSONString(body));
        // 参数校验
        this.verify(body);

        // 构建请求体参数
        String requestBody = this.buildXml(body);
        log.info(TAG + "xml参数：{}", requestBody);

        // 先执行连接请求
        if (connect) {
            this.connect();
        }

        // 执行请求
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Connection", "close");
            headers.put("Content-Type", "application/x-www-form-urlencoded");
//            Call<ResponseBody> call = ccbApi.execute(headers, URLEncoder.encode(requestBody, CHARSET_GB18030));
            Call<ResponseBody> call = ccbApi.execute(headers, requestBody);
            Response<ResponseBody> response = call.execute();

            String result;
            if (response.isSuccessful()) {
//                String resp = response.body().string();
//                result = new String(resp.getBytes(CHARSET_UTF_8), CHARSET_GB18030);       // 这里有坑 这种方式编码转换之后依旧是乱码 应该直接获取字节数据再编码
                ResponseBody responseBody = response.body();
                byte[] bytes = responseBody.bytes();
                result = new String(bytes, CHARSET_GB18030);

                log.info(TAG + "xml请求参数: {},  返回参数: {}", requestBody, result);

                // 直接解析
                CCBBaseResponse<T> ccbBaseResponse = XML_MAPPER.readValue(result, typeReference);
                String returnCode = ccbBaseResponse.getReturnCode();
                if (CCBReturnCodeEnum.SUCCESS.getCode().equals(returnCode)) {
                    ccbBaseResponse.setCode(MessagePack.OK);
                } else {
                    String desc = CCBReturnCodeEnum.getDesc(returnCode);
                    String message = StringUtils.isNotBlank(desc) ? desc : ccbBaseResponse.getReturnMsg();
                    ccbBaseResponse.setCode(response.code());                 // code怎么定义合理
                    ccbBaseResponse.setMessage(message);
                }
                return ccbBaseResponse;
            } else {
//                String resp = response.errorBody().string();
//                result = URLDecoder.decode(resp, CHARSET_GB18030);
                ResponseBody responseBody = response.errorBody();
                byte[] bytes = responseBody.bytes();
                result = new String(bytes, CHARSET_GB18030);
                log.error(TAG + "xml请求参数: {},  请求失败: {}", requestBody, result);
                CCBBaseResponse baseResponse = new CCBBaseResponse();
                baseResponse.setCode(response.code());
                baseResponse.setMessage(result);
                return baseResponse;
            }
        } catch (Exception e) {
            log.error(TAG + "系统错误。xml请求参数: {}", requestBody, e);
            CCBBaseResponse baseResponse = new CCBBaseResponse();
            baseResponse.setCode(MessagePack.EXCEPTION);
            baseResponse.setMessage("系统错误");
            baseResponse.setFullMessage(e.getMessage());
            return baseResponse;
        }

    }

    /**
     * 执行请求
     * @param body
     * @param typeReference
     * @param <T>
     * @return
     */
    @Deprecated
    public <T extends CCBResponseBody> T executeRequest(CCBRequestBody body, TypeReference<CCBBaseResponse<T>> typeReference) {
        CCBBaseResponse<T> ccbBaseResponse = this._executeRequest(body, typeReference);
        if (ccbBaseResponse == null) {
            throw new RuntimeException(TAG + "解析返回xml内容中的<TX_INFO>标签失败!");
        }
        return ccbBaseResponse.getCcbResponseBody();
    }

    private <T extends CCBResponseBody> CCBBaseResponse<T> _executeRequest(CCBRequestBody body, TypeReference<CCBBaseResponse<T>> typeReference) {
        String result = this.executeRequest(body, true);
        if (result == null) {
            throw new RuntimeException(TAG + "请求失败!");
        }
        try {
            CCBBaseResponse<T> ccbBaseResponse = XML_MAPPER.readValue(result, typeReference);
            return ccbBaseResponse;
        } catch (IOException e) {
            throw new RuntimeException(TAG + "解析返回xml失败!");
        }
    }

    /**
     * 执行请求
     * @param body
     */
    public String executeRequest(CCBRequestBody body, boolean connect) {
        log.info(TAG + "请求参数: " + JSONObject.toJSONString(body));
        // 参数校验
        this.verify(body);

        // 构建请求体参数
        String requestBody = this.buildXml(body);
        log.info(TAG + "xml参数：{}", requestBody);

        // 先执行连接请求 todo 我也不知道为什么每次请求外联平台客户端前都要连接一次
        if (connect) {
            this.connect();
        }

        // 执行请求
//        String result = sendHttpRequest(requestBody);
        try {
//            Call<ResponseBody> call = ccbApi.execute(params.length(), params.getBytes(CHARSET_GB2312));
            Map<String, String> headers = new HashMap<>();
            headers.put("Connection", "close");
            headers.put("Content-Type", "application/x-www-form-urlencoded");
//            String params = "requestXml=" + requestBody;
//            headers.put("Content-Length", String.valueOf(params.length()));
//            Call<ResponseBody> call = ccbApi.execute(headers, URLEncoder.encode(requestBody, CHARSET_GB18030));
            Call<ResponseBody> call = ccbApi.execute(headers, requestBody);
            Response<ResponseBody> response = call.execute();

            String result;
            if (response.isSuccessful()) {
//                String resp = response.body().string();
//                result = URLDecoder.decode(resp, CHARSET_GB18030);
                ResponseBody responseBody = response.body();
                byte[] bytes = responseBody.bytes();
                result = new String(bytes, CHARSET_GB18030);
                log.info(TAG + "xml请求参数: {},  返回参数: {}", requestBody, result);
            } else {
//                String resp = response.errorBody().string();
//                result = URLDecoder.decode(resp, CHARSET_GB18030);
                ResponseBody responseBody = response.errorBody();
                byte[] bytes = responseBody.bytes();
                result = new String(bytes, CHARSET_GB18030);
                log.error(TAG + "xml请求参数: {},  请求失败: {}", requestBody, result);
            }
            return result;
        } catch (Exception e) {
            log.error(TAG + "系统错误。xml请求参数: {}", requestBody, e);
        }

        return null;
    }

    /**
     * 连接
     */
    public void connect() {
        ConnectRequest connectRequest = new ConnectRequest();
        CCBBaseResponse<ConnectResponse> baseResponse = this.executeRequestNew(connectRequest, new TypeReference<CCBBaseResponse<ConnectResponse>>() {
        }, false);
        if (MessagePack.OK != baseResponse.getCode()) {
            throw new RuntimeException(TAG + "外联客户端连接失败!");
        }
    }

    /**
     * 构建xml参数
     * @param body
     * @return
     */
    private String buildXml(CCBRequestBody body) {
        // 封装基础参数
        CCBBaseRequest ccbBaseRequest = new CCBBaseRequest();
//        Long requestNo = SeqUtil.getNoSubSequence("cbaseinfo", "ccbpay_request_no");
        Long requestNo = idWorker.generate(Biz.UNUSED, PayBizDetail.CCB);
        ccbBaseRequest.setRequestSn(String.valueOf(requestNo));
        ccbBaseRequest.setCustId(custId);
        ccbBaseRequest.setTxCode(body.getTxCode());
        ccbBaseRequest.setPassword(password);
        ccbBaseRequest.setUserId(userId);

        ccbBaseRequest.setCcbRequestBody(body);

        // 生成xml字符串
        String xmlStr = null;
        try {
            xmlStr = XML_MAPPER.writeValueAsString(ccbBaseRequest);
            return xmlHeader + xmlStr;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(TAG + "生成xml字符串异常", e);
        }

    }

    /**
     * 入参校验 todo 自定义注解 校验参数是否必填、长度限制等
     * @param body
     */
    private void verify(CCBRequestBody body) {
        if (body == null) {
            throw new RuntimeException(TAG + "参数不能为空!");
        }
        // ...
    }

    /**
     * 验签(异步回调用)
     * @param params
     * @return
     */
    public boolean verifySign(NotifyParams params) {
        // 生成签名原始串
        StringBuffer sb = new StringBuffer();
        sb.append("POSID").append("=").append(params.getPosId()).append("&")
            .append("BRANCHID").append("=").append(params.getBranchId()).append("&")
            .append("ORDERID").append("=").append(params.getOrderId()).append("&")
            .append("PAYMENT").append("=").append(params.getPayment()).append("&")
            .append("CURCODE").append("=").append(params.getCurCode()).append("&")
            .append("REMARK1").append("=").append(params.getRemark1()).append("&")
            .append("REMARK2").append("=").append(params.getRemark2()).append("&")
            .append("ACC_TYPE").append("=").append(params.getAccType()).append("&")
            .append("SUCCESS").append("=").append(params.getSuccess()).append("&");
        if (params.getType() != null) {
            sb.append("TYPE").append("=").append(params.getType()).append("&");
        }
        if (params.getReferer() != null) {
            sb.append("REFERER").append("=").append(params.getReferer()).append("&");
        }
        if (params.getClientip() != null) {
            sb.append("CLIENTIP").append("=").append(params.getClientip()).append("&");
        }
        if (params.getAccDate() != null) {
            sb.append("ACCDATE").append("=").append(params.getAccDate()).append("&");
        }
        if (params.getInstallNum() != null) {
            sb.append("INSTALLNUM").append("=").append(params.getInstallNum()).append("&")
              .append("ERRMSG").append("=").append(params.getErrMsg()).append("&");
        }
        if (params.getUsrMsg() != null) {
            sb.append("USRMSG").append("=").append(params.getUsrMsg()).append("&");
        }
        if (params.getUsrInfo() != null) {
            sb.append("USRINFO").append("=").append(params.getUsrInfo()).append("&");
        }
        if (params.getDiscount() != null) {
            sb.append("DISCOUNT").append("=").append(params.getDiscount()).append("&");
        }
        if (params.getZhJf() != null) {
            sb.append("ZHJF").append("=").append(params.getZhJf()).append("&");
        }
        if (params.getOpenid() != null) {
            sb.append("OPENID").append("=").append(params.getOpenid()).append("&");
        }
        if (params.getSubOpenid() != null) {
            sb.append("SUB_OPENID").append("=").append(params.getSubOpenid()).append("&");
        }
        if (params.getPaymentDetails() != null) {
            sb.append("PAYMENT_DETAILS").append("=").append(params.getPaymentDetails()).append("&");
        }
        String oriStr = sb.substring(0, sb.length() - 1);

        // 验签
        RSASig rsaSig = new RSASig();
        rsaSig.setPublicKey(publicKey);
        log.debug("建行异步回调，准备验签。 签名原始串：{},  返回的签名参数: {}", oriStr, params.getSign());
        boolean verifyResult = rsaSig.verifySigature(params.getSign(), oriStr);
        return verifyResult;
    }

    /**
     * 支付账户信息USRMSG解密
     * @param usrMsg
     * @return
     */
    public String decodeUsrMsg(String usrMsg) {
        try {
            // 对称加密的密钥为商户公钥后30位
            String key = publicKey.substring(publicKey.length() - 30);
            // 解密
            MCipherDecode mcd = new MCipherDecode(key);
            String decodedString = mcd.getDecodeString(usrMsg);
            // 进行字符转码
            byte[] tempByte = decodedString.getBytes(CHARSET_ISO_8859_1);
            String a = new String(tempByte, CHARSET_GBK);
            return a;
        } catch (Exception e) {
            throw new RuntimeException("支付账户信息USRMSG解密失败!", e);
        }
    }

    public void prepare(PayRequest request) {
        // 设置基础参数
        request.setMerchantId(custId);
        request.setPosId(posId);
        request.setBranchId(branchId);
        request.setPub(publicKey.substring(publicKey.length() - 30));
//        request.setType("1");
//        request.setGateway("0");
//        request.setReferer("");
    }

    /**
     * 组装支付参数 pc端支付可以由后端拼接参数并计算mac，移动端、app需要前端自行处理
     */
    public String prePay(PayRequest request) throws IllegalAccessException {
        // 设置基础参数
        request.setMerchantId(custId);
        request.setPosId(posId);
        request.setBranchId(branchId);
        request.setPub(publicKey.substring(publicKey.length() - 30));
        request.setType("1");
        request.setGateway("0");
        request.setReferer("");

        request.setMac(calMac(request));
        request.setPub(null);// 公钥计算mac用 不需要传参 因此重新置为null


        // 转成kv格式
        StringBuilder sb = new StringBuilder();
        Class clazz = PayRequest.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            String fieldName = field.getName().toUpperCase();   // 字段名默认大写
            String fieldValue = (String)field.get(request);

            CCBPayField annotation = field.getAnnotation(CCBPayField.class);
            if ((annotation == null || !annotation.required()) && fieldValue == null) {
                // 非必填字段且字段值为null时 不传
                continue;
            }
            if (annotation != null) {
                if (annotation.required() && fieldValue == null) {
                    throw new RuntimeException("参数[" + fieldName + "]值不能为空");
                }
                if (StringUtils.isNotBlank(annotation.name())) {
                    fieldName = annotation.name();
                }
                if (StringUtils.isNotBlank(fieldValue) && annotation.needEscape()) {
                    fieldValue = Escape.escape(fieldValue);
                }
            }

            sb.append(fieldName).append("=").append(fieldValue).append("&");
        }

        String payParams = sb.substring(0, sb.length() - 1);

        return payParams;
    }

    /**
     * 计算mac值 使用标准的md5算法
     */
    public String calMac(PayRequest request) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("MERCHANTID", request.getMerchantId());
        params.put("POSID", request.getPosId());
        params.put("BRANCHID", request.getBranchId());
        params.put("ORDERID", request.getOrderId());
        params.put("PAYMENT", request.getPayment());
        params.put("CURCODE", request.getCurCode());
        params.put("TXCODE", request.getTxCode());
        params.put("REMARK1", request.getRemark1());
        params.put("REMARK2", request.getRemark2());
        params.put("TYPE", request.getType());
        params.put("PUB", request.getPub());
        params.put("GATEWAY", request.getGateway());
        if (request.getClientIp() != null) {
            params.put("CLIENTIP", request.getClientIp()); // CLIENTIP REFER REGINFO 如果为null则以空串""参与mac计算
        } else {
            params.put("CLIENTIP", "");
        }
        if (request.getRegInfo() != null) {
            params.put("REGINFO", Escape.escape(request.getRegInfo()));
        } else {
            params.put("REGINFO", "");
        }
        if (request.getProInfo() != null) {
            params.put("PROINFO", Escape.escape(request.getProInfo()));
        } else {
            params.put("PROINFO", "");
        }
        if (request.getReferer() != null) {
            params.put("REFERER", request.getReferer());
        } /*else {
           params.put("REFERER", "");
        } */

        // 以下所有字段有值时才参与mac计算
        if (StringUtil.isNotNullOrBlankTrim(request.getInstallNum())) {
            params.put("INSTALLNUM", request.getInstallNum());
        }
        // 这8个字段都有值是才参与mac计算
        if (StringUtil.isAllNotNullOrBlank(request.getSMerId(), request.getSMerName(), request.getSMerTypeId(), request.getSMerType(),
                request.getTradeCode(), request.getTradeName(), request.getSMeProType(), request.getProName())) {
            params.put("SMERID", request.getSMerId());
            params.put("SMERNAME", Escape.escape(request.getSMerName()));
            params.put("SMERTYPEID", request.getSMerTypeId());
            params.put("SMERTYPE", Escape.escape(request.getSMerType()));
            params.put("TRADECODE", request.getTradeCode());
            params.put("TRADENAME", Escape.escape(request.getTradeName()));
            params.put("SMEPROTYPE", request.getSMeProType());
            params.put("PRONAME", Escape.escape(request.getProName()));
        }
        if (StringUtil.isNotNullOrBlankTrim(request.getThirdAppInfo())) {
            params.put("THIRDAPPINFO", request.getThirdAppInfo());
        }
        if (StringUtil.isNotNullOrBlankTrim(request.getTimeout())) {
            params.put("TIMEOUT", request.getTimeout());
        }

        log.info("ccbClient calMac input:{}", JSONObject.toJSONString(params));

        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            stringBuilder.append(key).append("=").append(value).append("&");
        }

        String paramStr = stringBuilder.substring(0, stringBuilder.length() - 1);
        String result = MD5Utils.getMD5Code(paramStr);
        log.info("ccbClient calMac result:{}", result);
        return result;
    }

    private String sendHttpRequest(String sRequest) {
        String sResult = "";
        OutputStream out = null;
        BufferedReader in = null;
        try {
            String encoding = CHARSET_GB18030;
            String params = "requestXml=" + URLEncoder.encode(sRequest, encoding); // http请求需将报文放在requestXml参数发送

//            String path = "http://" + ipAddreess + ":" + nPort;
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            // 设置连接超时时间
            conn.setConnectTimeout(10 * 1000);

            conn.setDoOutput(true); // 将doOutput标志设置为true，指示应用程序要将数据写入URL连接
            conn.setDoInput(true); // 将doInput标志设置为true，指示应用程序要从URL连接读取数据

            // 设置http请求报文头
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(params.length()));
            conn.setRequestProperty("Connection", "close");

            // 发送请求报文数据
            out = conn.getOutputStream();
            out.write(params.getBytes(encoding));
            out.flush();
            out.close();

            // 读取返回数据
            if (conn.getResponseCode() == 200) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
            } else {
                in = new BufferedReader(new InputStreamReader(conn.getErrorStream(), encoding));
            }

            String sLine = null;
            StringBuffer sb = new StringBuffer();
            while ((sLine = in.readLine()) != null) {
                sb.append(sLine);
            }
            sResult = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sResult;
    }

    private String sendSocketRequest(String ipAddress, int nPort, String sRequest) {
        String sResult = "";
        try {
            Socket socket = new Socket(ipAddress, nPort);
            BufferedReader ins = new BufferedReader(new InputStreamReader(socket.getInputStream(), CHARSET_GB18030));
            PrintWriter outs = new PrintWriter(socket.getOutputStream());

            outs.print(sRequest);
            outs.flush();
            socket.shutdownOutput();

            String sLine = null;
            StringBuffer sb = new StringBuffer();
            while ((sLine = ins.readLine()) != null) {
                sb.append(sLine);
            }

            ins.close();
            outs.close();
            socket.close();

            return sb.toString();

        } catch (NumberFormatException e) {
            sResult = e.getMessage();
        } catch (UnknownHostException e) {
//            e.printStackTrace();
            sResult = e.getMessage();
        } catch (IOException e) {
//            e.printStackTrace();
            sResult = e.getMessage();
        }
        return sResult;
    }

}
