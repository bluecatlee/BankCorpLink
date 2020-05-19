package com.github.bluecatlee.cib.execute;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.base.BaseReq;
import com.github.bluecatlee.cib.base.BaseResp;
import com.github.bluecatlee.cib.base.Status;
import com.github.bluecatlee.cib.base.TransferProcessResult;
import com.github.bluecatlee.cib.base.request.*;
import com.github.bluecatlee.cib.base.response.BusinessResp;
import com.github.bluecatlee.cib.base.response.RespParams;
import com.github.bluecatlee.cib.base.response.SecuritiesMsgResp;
import com.github.bluecatlee.cib.constant.Constants;
import com.github.bluecatlee.cib.constant.XferPrcConstants;
import com.github.bluecatlee.cib.exception.CibException;
import com.github.bluecatlee.cib.logger.CibLogger;
import com.github.bluecatlee.cib.utils.OkHttpUtils;
import com.github.bluecatlee.cib.utils.ReflectUtils;
import com.github.bluecatlee.cib.valid.Validator;
import com.github.bluecatlee.common.id.configuration.IdWorker;
import com.github.bluecatlee.common.id.enumeration.Biz;
import com.github.bluecatlee.common.id.enumeration.PayBizDetail;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Caller {

    private static final XmlMapper XML_MAPPER = new XmlMapper();
    // private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(Caller.class);
    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"GBK\"?>";
    private static final String LOG_PREFIX = "CIB >>>>>>>>>>>";
    private static final String TAG_NAME = "businessReq";
    private static final String PROP_NAME = "localName";

    private static final Map<String, String> MSG_TAG_NAME_MAP = new HashMap(2);
    private static final String MSG_TAG_NAME_KEY = "MSG_TAG_NAME_KEY";
    private static final String DEFAULT_MSG_TAG_NAME = "SECURITIES_MSGSRSV1";

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    static {
        // 设置命名策略后 @JacksonXmlRootElement 会失效
        // XML_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        // null值不输出
        XML_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 遇到未知字段不抛出异常
        XML_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Value("${cib.http.host}")
    private String host;
    @Value("${cib.http.port}")
    private String port;
    @Value("${cib.cid}")
    private String cid;
    @Value("${cib.userid}")
    private String userId;
    @Value("${cib.userpass}")
    private String userPass;

    @Autowired
    private IdWorker idWorker;

    /**
     * 日志数据记录 业务方调用者实现
     */
    @Autowired(required = false)
    private CibLogger cibLogger;

    /**
     * 发起调用
     *      直接返回xml字符串 由调用者单独解析
     * @param reqParams
     * @param requestSeqId
     * @return
     */
    @SuppressWarnings("all")
    public String execute(ReqParams reqParams) {
        return execute(reqParams, null);
    }

    /**
     * 发起调用
     *      直接返回xml字符串 由调用者单独解析
     * @param reqParams
     * @param requestSeqId
     * @return
     */
    @SuppressWarnings("all")
    public String execute(ReqParams reqParams, String requestSeqId) {
        String request = build(reqParams, requestSeqId);
        String result = null;
        try {
            LOGGER.debug(LOG_PREFIX + "http call request:" + request);
            byte[] bytes = request.getBytes(Constants.DEFAULT_CHARSET);
            result = OkHttpUtils.request("http://" + host + ":" + port, bytes, null);
            LOGGER.debug(LOG_PREFIX + "http call response:" + result);
            this.log(reqParams.getBizTag(), request, result);
        } catch(Exception e) {
            LOGGER.error(LOG_PREFIX + "调用异常: ", e);
            this.log(reqParams.getBizTag(), request, result);
            throw new CibException("调用异常", e);
        }

        return result;
    }

    /**
     * 发起调用
     *      通用的解析方法难以复用且过于复杂 并发不安全 如果加锁则会影响性能
     * @param reqParams
     * @param requestSeqId
     * @param typeReference
     * @return
     */
    @SuppressWarnings("all")
    @Deprecated
    public <T extends RespParams> BusinessResp<T> execute(ReqParams reqParams, String requestSeqId, TypeReference<BusinessResp<T>> typeReference) {

        String request = build(reqParams, requestSeqId);
        String result = null;
        try {
            LOGGER.debug(LOG_PREFIX + "http call request:" + request);
            byte[] bytes = request.getBytes(Constants.DEFAULT_CHARSET);
            // Map<String, String> headers = new HashMap<>();
            // headers.put("Content-Type", Constants.CONTENT_TYPE1);
            // headers.put("Content-Length", String.valueOf(bytes.length));
            result = OkHttpUtils.request("http://" + host + ":" + port, bytes, null);
            LOGGER.debug(LOG_PREFIX + "http call response:" + result);
            this.log(reqParams.getBizTag(), request, result);
        } catch(Exception e) {
            LOGGER.error(LOG_PREFIX + "调用异常: ", e);
            this.log(reqParams.getBizTag(), request, result);
            throw new CibException("调用异常", e);
        }

        return parse(result, reqParams.getRespBizTag(), reqParams.getResultWrapperTag(), typeReference);

    }

    /**
     * 构建请求内容
     *      说明：必填字段必须要有对应的xml标签
     * @param reqParams
     * @param requestSeqId
     * @return
     */
    private String build(ReqParams reqParams, String requestSeqId) {

        // 参数校验
        Validator.validate(reqParams);

        BaseReq baseReq = new BaseReq();
        SignonMsgReq signonMsgReq = new SignonMsgReq();
        SignonReq signOnReq = new SignonReq();
        signOnReq.setClientDateTime(new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date()));
        signOnReq.setCid(cid);
        signOnReq.setUserId(userId);
        signOnReq.setUserPass(userPass);
        signonMsgReq.setSignonReq(signOnReq);
        baseReq.setSignonMsgReq(signonMsgReq);

        SecuritiesMsgReq securitiesMsgReq = new SecuritiesMsgReq();
        BusinessReq businessReq = new BusinessReq();
        String trnuid = requestSeqId;
        if (StringUtils.isBlank(requestSeqId)) {
            trnuid = String.valueOf(idWorker.generate(Biz.PAY, PayBizDetail.CIB));
        }
        setSequence(trnuid);
        businessReq.setTrnuid(trnuid);
        businessReq.setReqParams(reqParams);
        securitiesMsgReq.setBusinessReq(businessReq);
        baseReq.setSecuritiesMsgReq(securitiesMsgReq);

        String bizTag = reqParams.getBizTag();
        String paramsWrapperTag = reqParams.getParamsWrapperTag();
        String resultWrapperTag = reqParams.getResultWrapperTag();
        // boolean replaceFlag = false;
        // try {
        //     // 动态设置注解属性值
        //     ReflectUtils.modAnnotationVal(SecuritiesMsgReq.class, TAG_NAME, JacksonXmlProperty.class, PROP_NAME, bizTag);
        //     ReflectUtils.modAnnotationVal(BusinessReq.class, ReqParams.class, JacksonXmlProperty.class, PROP_NAME, paramsWrapperTag);
        // } catch (Exception e) {
        //     LOGGER.warn(LOG_PREFIX + "动态设置注解属性值异常: ", e);
        //     // 异常 需要进行文本替换
        //     replaceFlag = true;
        // }

        String xmlStr = null;
        try {
            xmlStr = XML_MAPPER.writeValueAsString(baseReq);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // if (replaceFlag) {
        //     xmlStr = xmlStr.replace("<" + TAG_NAME + ">", "<" + bizTag + ">")
        //             .replace("</" + TAG_NAME + ">", "</" + bizTag + ">");
        // }

        // 替换方案
        xmlStr = xmlStr.replace(TAG_NAME, bizTag);
        if (!"RQBODY".equals(paramsWrapperTag)) {
            xmlStr = xmlStr.replace("RQBODY", paramsWrapperTag);
        }

        return XML_HEADER + xmlStr;
    }

    private <T extends RespParams> BusinessResp<T> parse(String response, String respBizTag, String resultWrapperTag, TypeReference<BusinessResp<T>> typeReference) {

        if (StringUtils.isBlank(response)) {
            throw new CibException("解析响应数据失败: xml内容为空");
        }
        if (StringUtils.isBlank(respBizTag)) {
            throw new CibException("解析响应数据失败: 未获取到对应业务的标签名");
        }

        String annotationVal = null;
        try {
            annotationVal = MSG_TAG_NAME_MAP.get(MSG_TAG_NAME_KEY);
            if (StringUtils.isBlank(annotationVal)) {
                annotationVal = (String)ReflectUtils.getAnnotationVal(BaseResp.class, SecuritiesMsgResp.class, JacksonXmlProperty.class, PROP_NAME);
                MSG_TAG_NAME_MAP.put(MSG_TAG_NAME_KEY, annotationVal);
            }
            if (!"RSBODY".equals(resultWrapperTag)) {
                ReflectUtils.modAnnotationVal(BusinessResp.class, "respParams", JacksonXmlProperty.class, PROP_NAME, resultWrapperTag);
            }
        } catch (Exception e) {
        }
        if (StringUtils.isBlank(annotationVal)) {
            annotationVal = DEFAULT_MSG_TAG_NAME;
        }

        BusinessResp<T> businessResp = null;
        try {
            JsonNode rootNode = XML_MAPPER.readTree(response);
            JsonNode msgNode = rootNode.get(annotationVal);
            JsonNode bizNode = msgNode.get(respBizTag);
            String s = XML_MAPPER.writeValueAsString(bizNode);

            // todo 优化 按照node的方式解析会变成json 但是json无法处理tag中的属性
            // if (s.contains("<MORE>")) {
            //     s = s.replace("<MORE>[YN]</MORE>", "");
            // }

            businessResp = XML_MAPPER.readValue(s, typeReference);
            // BusinessResp<T> businessResp1 = OBJECT_MAPPER.readValue(bizNode.toString(), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 校验请求是否成功 并记录日志 todo
        if (businessResp != null) {

            // log todo

            Status status = businessResp.getStatus();
            if ("0".equals(status.getCode())) {
                TransferProcessResult xferprcsts = businessResp.getXferprcsts();
                if (xferprcsts != null && XferPrcConstants.PAYOUT.equals(xferprcsts.getCode())) {
                    // 成功
                    return businessResp;
                }
            }

        }

        return null;
    }

    protected void log(String type, String reqMsg, String respMsg) {
        this.log(type, reqMsg, respMsg, null, null, null, null, null, null);
    }

    protected void log(String type, String reqMsg, String respMsg, String trnuid, String code, String message, String xcode, String xmessage, String info) {
        if (cibLogger != null) {
            Map<String, String> params = new HashMap<>();
            params.put("sequence", getSequence());
            params.put("type", type);
            params.put("reqMsg", reqMsg);
            params.put("respMsg", respMsg);
            params.put("trnuid", trnuid);
            params.put("code", code);
            params.put("message", message);
            params.put("xcode", xcode);
            params.put("xmessage", xmessage);
            params.put("info", info);
            cibLogger.log(params);
        }
    }

    protected void setSequence(String val) {
        threadLocal.set(val);
    }

    public String getSequence() {
        return threadLocal.get();
    }

    public void removeSequence() {
        threadLocal.remove();
    }

}
