package com.github.bluecatlee.bcm.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.bluecatlee.bcm.bean.collection.*;
import com.github.bluecatlee.bcm.bean.info.AccountInfo;
import com.github.bluecatlee.bcm.bean.info.AccountInfoReq;
import com.github.bluecatlee.bcm.bean.info.AccountInfoResp;
import com.github.bluecatlee.bcm.bean.protocol.QuerySubProtocolReq;
import com.github.bluecatlee.bcm.bean.protocol.QuerySubProtocolReq2;
import com.github.bluecatlee.bcm.bean.protocol.QuerySubProtocolResp;
import com.github.bluecatlee.bcm.bean.protocol.SubProtocol;
import com.github.bluecatlee.common.id.configuration.IdWorker;
import com.github.bluecatlee.common.id.enumeration.Biz;
import com.github.bluecatlee.common.id.enumeration.PayBizDetail;
import com.github.bluecatlee.bcm.constant.Constants;
import com.github.bluecatlee.bcm.exception.BcmException;
import com.github.bluecatlee.bcm.handler.BcmPostHandler;
import com.github.bluecatlee.bcm.handler.factory.AbstractHandlerFactory;
import com.github.bluecatlee.bcm.invoke.Call;
import com.github.bluecatlee.bcm.parser.MFSParser;
import com.github.bluecatlee.bcm.request.BcmReqHeader;
import com.github.bluecatlee.bcm.request.BcmReqParam;
import com.github.bluecatlee.bcm.request.BcmRequest;
import com.github.bluecatlee.bcm.response.BcmRespHeader;
import com.github.bluecatlee.bcm.response.BcmResponse;
import com.github.bluecatlee.bcm.service.BcmService;
import com.github.bluecatlee.bcm.valid.Validator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("bcmService")
public class BcmServiceImpl implements BcmService {

    @Value("${bcm.corp-no}")
    private String corpNo;
    @Value("${bcm.user-no}")
    private String userNo;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private Call call;

    @Autowired
    private AbstractHandlerFactory abstractHandlerFactory;

    private static final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public AccountInfo queryAccountInfo(String accountNo) {
        if (StringUtils.isBlank(accountNo)) {
            throw new BcmException("账户号不能为空");
        }
        List<AccountInfo> accountInfos = queryAccountInfo(new String[]{accountNo});
        if (accountInfos != null && accountInfos.size() > 0) {
            return accountInfos.get(0);
        }
        return null;
    }

    @Override
    public List<AccountInfo> queryAccountInfo(String[] accountNos) {
        if (accountNos == null) {
            throw new BcmException("账户号参数不能为空");
        }
        if (accountNos.length > 50) {
            throw new BcmException("最大支持50个账户批量查询");
        }
        BcmReqParam[] params = new BcmReqParam[accountNos.length];
        for (int i = 0; i < accountNos.length; i++) {
            AccountInfoReq param = new AccountInfoReq();
            param.setAcno(accountNos[i]);
            params[i] = param;
        }
        String req = generateRequestMessage(params);
        String resp = "";
        try {
            resp = (String)call.execute(req);
        } catch (Exception e) {
            throw new BcmException(e);
        }

        BcmResponse<AccountInfoResp> bcmResponse = parseResponseMessage(resp, new TypeReference<BcmResponse<AccountInfoResp>>() {});

        if (bcmResponse != null && bcmResponse.getBody() != null) {
            AccountInfoResp body = bcmResponse.getBody();
            Integer fieldNum = body.getFieldNum();  // 字段数
            Integer recordNum = body.getRecordNum(); // 记录数
            String serialRecord = body.getSerialRecord(); // 多域串
            String fileFlag = body.getFileFlag(); // 文件标识
            String filename = body.getFilename(); // 文件名

            if (recordNum <= 0 || fieldNum <= 0 ) {
                return null;
            }

            if (StringUtils.isBlank(serialRecord) || Constants.FILE_FLAG.equals(fileFlag)) {
                // 读取文件
            }

            List<AccountInfo> accountInfos = MFSParser.handleMfs(serialRecord, Constants.SEPERATOR, fieldNum, recordNum, AccountInfo.class);

            return accountInfos;
        }
        return null;
    }

    @Override
    public List<SubProtocol> querySubProtocol(String cagrNo) {
        if (StringUtils.isBlank(cagrNo)) {
            throw new BcmException("缺少收款协议编号参数");
        }
        QuerySubProtocolReq q = new QuerySubProtocolReq();
        q.setCagrNo(cagrNo);
        return querySubProtocolX(q);
    }

    @Override
    public List<SubProtocol> querySubProtocol(QuerySubProtocolReq2 query) {
        return querySubProtocolX(query);
    }

    @Override
    public SubProtocol querySubProtocol(String cagrNo, String accNo, String payAcname) {
        if (StringUtils.isBlank(cagrNo)) {
            throw new BcmException("收款协议编号不能为空");
        }
        // if (StringUtils.isBlank(payAcname)) {
        //     throw new BcmException("付款户名");
        // }
        QuerySubProtocolReq2 req = new QuerySubProtocolReq2();
        req.setCagrNo(cagrNo);
        req.setAccNo(accNo);
        req.setAccName(payAcname);
        List<SubProtocol> subProtocols = querySubProtocol(req);
        if (subProtocols != null && subProtocols.size() > 1) {
            throw new BcmException("系统异常: 查询到多个子协议!");
        }
        return subProtocols == null ? null : subProtocols.get(0);
    }

    private List<SubProtocol> querySubProtocolX(QuerySubProtocolReq query) {
        if (query == null) {
            throw new BcmException("缺少参数");
        }
        if (StringUtils.isBlank(query.getCagrNo())) {
            throw new BcmException("缺少主协议编号");
        }
        if (query.getPageSize() != null && (query.getPageSize().intValue() > 50 || query.getPageSize().intValue() < 0)) {
            throw new BcmException("单次查询笔数不合法");
        }
        if (query.getBeginPos() != null && query.getBeginPos() < 0) {
            throw new BcmException("查询起始位置值不合法");
        }
        String req = generateRequestMessage(query);
        String resp = "";
        try {
            resp = (String)call.execute(req);
        } catch (Exception e) {
            throw new BcmException(e);
        }
        BcmResponse<QuerySubProtocolResp> bcmResponse = parseResponseMessage(resp, new TypeReference<BcmResponse<QuerySubProtocolResp>>() {});

        if (bcmResponse != null && bcmResponse.getBody() != null) {
            QuerySubProtocolResp body = bcmResponse.getBody();
            Integer fieldNum = body.getFieldNum();  // 字段数
            Integer recordNum = body.getRecordNum(); // 记录数
            String serialRecord = body.getSerialRecord(); // 多域串
            if (recordNum <= 0 || fieldNum <= 0 ) {
                return null;
            }
            List<SubProtocol> subProtocols = MFSParser.handleMfs(serialRecord, Constants.SEPERATOR, fieldNum, recordNum, SubProtocol.class);
            return subProtocols;
        }
        return null;
    }

    @Override
    public String batchCollection(BatchCollec batchCollec) {
        if (batchCollec == null) {
            throw new BcmException("缺少参数");
        }

        String req = generateRequestMessage(batchCollec);
        String resp = "";
        try {
            resp = (String)call.execute(req);
        } catch (Exception e) {
            throw new BcmException(e);
        }
        BcmResponse<BatchCollecResult> bcmResponse = parseResponseMessage(resp, new TypeReference<BcmResponse<BatchCollecResult>>() {});
        if (bcmResponse != null && bcmResponse.getBody() != null) {
            String ebankBatFlw = bcmResponse.getBody().getEbankBatFlw();  // 网银批次号
            return ebankBatFlw;
        }
        return null;
    }

    @Override
    public List<BatchCollecDetail> queryCollectionByFlwno(String flwno) {
        if (StringUtils.isBlank(flwno)) {
            throw new BcmException("网银流水号为空");
        }
        QueryBatchCollecReq query = new QueryBatchCollecReq();
        query.setFlwno(flwno);
        query.setQueryFlag("2");
        return queryCollection(query);
    }

    @Override
    public List<BatchCollecDetail> queryCollectionByDealNo(String dealNo) {
        if (StringUtils.isBlank(dealNo)) {
            throw new BcmException("企业凭证号为空");
        }
        QueryBatchCollecReq query = new QueryBatchCollecReq();
        query.setFlwno(dealNo);
        return queryCollection(query);
    }

    @Override
    public List<BatchCollecDetail> queryCollection(QueryBatchCollecReq query) {
        if (query == null) {
            throw new BcmException("缺少参数");
        }
        if (StringUtils.isBlank(query.getFlwno())) {
            throw new BcmException("网银流水号为空");
        }
        String req = generateRequestMessage(query);
        String resp = "";
        try {
            resp = (String)call.execute(req);
        } catch (Exception e) {
            throw new BcmException(e);
        }
        BcmResponse<QueryBatchCollecResp> bcmResponse = parseResponseMessage(resp, new TypeReference<BcmResponse<QueryBatchCollecResp>>() {});
        if (bcmResponse != null && bcmResponse.getBody() != null) {
            QueryBatchCollecResp body = bcmResponse.getBody();
            Integer fieldNum = body.getFieldNum();  // 字段数
            Integer recordNum = body.getRecordNum(); // 记录数
            String serialRecord = body.getSerialRecord(); // 多域串
            if (recordNum <= 0 || fieldNum <= 0 ) {
                return null;
            }
            List<BatchCollecDetail> batchCollecDetails = MFSParser.handleMfs(serialRecord, Constants.SEPERATOR, fieldNum, recordNum, BatchCollecDetail.class);
            return batchCollecDetails;
        }
        return null;
    }

    private String generateRequestMessage(BcmReqParam... params) {
        // 参数校验
        try {
            Validator.validate(params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // 转成xml
        // 设置header参数
        BcmReqHeader bcmReqHeader = new BcmReqHeader();
        bcmReqHeader.setCorpNo(corpNo);
        bcmReqHeader.setUserNo(userNo);
        bcmReqHeader.setReqNo(String.valueOf(idWorker.generate(Biz.PAY, PayBizDetail.BCM))); // 发起方序号使用雪花算法产生 按需修改算法内容
        bcmReqHeader.setAtomTrCount(String.valueOf(params.length));
        bcmReqHeader.setTrCode(params[0].getTrCode());

        BcmRequest bcmRequest = BcmRequest.builder().header(bcmReqHeader).params(Arrays.asList(params)).build();

        String xmlStr = null;
        try {
            // xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE); // @JacksonXmlRootElement会失效
            xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);      // null值不输出
            xmlStr = xmlMapper.writeValueAsString(bcmRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return xmlStr;
    }

    // /**
    //  * 通用的反序列化响应xml字符串为业务bean的方法 deprecated
    //  * @param respXmlStr
    //  * @param <T>
    //  * @return
    //  */
    // private <T> BcmResponse<T> dealResponseMessage(String respXmlStr, Class<T> clazz) {
    //     if (StringUtils.isBlank(respXmlStr)) {
    //         return null;
    //     }
    //     try {
    //         BcmResponse bcmResponse = xmlMapper.readValue(respXmlStr, BcmResponse.class);
    //         Object body = bcmResponse.getBody(); // LinkedHashMap
    //         Map<String, Object> mapBody = CamelUnderlineUtils.underlineToCamel((Map<String, Object>) body);
    //         T o = MapUtils.map2bean(mapBody, clazz); // map转bean需要处理字段类型 uncompleted
    //         bcmResponse.setBody(o);
    //         return bcmResponse;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

    private <T> T parseResponseMessage(String respXmlStr, TypeReference<T> typeReference) {
        try {
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 遇到未知字段不抛出异常
            T t = xmlMapper.readValue(respXmlStr, typeReference);
            // if (t != null) {
                // BcmResponse bcmResponse = (BcmResponse) t;
                // dealResponseHeader(bcmResponse.getHeader());
            // }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void dealResponseHeader(BcmRespHeader header) {
        BcmPostHandler handler = null;
        try {
            handler = abstractHandlerFactory.create(Constants.DEFAULT_HANDLER);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        if (handler == null) {
            return;
        }
        handler.handle(header);
    }

    private void dealResponseHeader(BcmRespHeader header, String handlerName) {
        BcmPostHandler handler = null;
        if (StringUtils.isNotBlank(handlerName)) {
            try {
                handler = abstractHandlerFactory.create(handlerName);
            } catch (Exception e) {
                // e.printStackTrace();
            }
        }
        if (handler == null) {
            return;
        }
        handler.handle(header);
    }

    private void dealResponseHeader(BcmRespHeader header, BcmPostHandler handler) {
        if (handler == null) {
            return;
        }
        handler.handle(header);
    }

}
