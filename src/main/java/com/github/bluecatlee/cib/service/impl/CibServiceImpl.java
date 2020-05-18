package com.github.bluecatlee.cib.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.bluecatlee.cib.base.Status;
import com.github.bluecatlee.cib.base.TransferProcessResult;
import com.github.bluecatlee.cib.base.response.BusinessResp;
import com.github.bluecatlee.cib.bean.TransferResult;
import com.github.bluecatlee.cib.bean.accquery.request.AccountQuery;
import com.github.bluecatlee.cib.bean.accquery.response.AccountQueryResult;
import com.github.bluecatlee.cib.bean.vsa.AccountForm;
import com.github.bluecatlee.cib.bean.vsa.SubAcctInfo;
import com.github.bluecatlee.cib.bean.vsa.request.*;
import com.github.bluecatlee.cib.bean.vsa.result.*;
import com.github.bluecatlee.cib.constant.XferPrcEnum;
import com.github.bluecatlee.cib.exception.CibException;
import com.github.bluecatlee.cib.execute.Caller;
import com.github.bluecatlee.cib.service.CibService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@SuppressWarnings("all")
public class CibServiceImpl implements CibService {

    private static final XmlMapper XML_MAPPER = new XmlMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(CibServiceImpl.class);
    private static final String LOG_PREFIX = "CIB >>>>>>>>>>>";

    static {
        XML_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        XML_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Autowired
    private Caller caller;

    @Override
    public AccountQueryResult queryAccount(String mainAcct) {
        AccountQuery accountQuery = new AccountQuery();
        accountQuery.setAcctid(mainAcct);
        // BusinessResp<AccountQueryResult> result = caller.execute(accountQuery, new TypeReference<BusinessResp<AccountQueryResult>>() {});

        String xmlStr = caller.execute(accountQuery);
        BusinessResp<AccountQueryResult> businessResp = null;
        try {
            JsonNode rootNode = XML_MAPPER.readTree(xmlStr);
            JsonNode bizNode = rootNode.get("SECURITIES_MSGSRSV1").get("ACCOUNTQUERYTRNRS");
            String s = XML_MAPPER.writeValueAsString(bizNode);
            businessResp = XML_MAPPER.readValue(s, new TypeReference<BusinessResp<AccountQueryResult>>() {});

        } catch (IOException e) {
            throw new CibException("解析异常", e);
        }

        if (businessResp == null) {
            throw new CibException("解析异常：解析结果为空");
        }

        String code = businessResp.getStatus().getCode();
        if (!"0".equals(code)) {
            throw new CibException("查询失败： 交易处理状态异常");
        }

        return businessResp.getRespParams();

    }

    @Override
    public Boolean vsaSign(String mainAcct) {
        Sign sign = new Sign();
        sign.setMainAcct(mainAcct);
        //签约
        sign.setSignFlag("Y");
        // BusinessResp<SignResult> result = caller.execute(sign, new TypeReference<BusinessResp<SignResult>>() {});
        // if (result == null || result.getRespParams() == null) {
        //     return false;
        // }

        String xmlStr = caller.execute(sign);
        BusinessResp<SignResult> businessResp = null;
        try {
            JsonNode rootNode = XML_MAPPER.readTree(xmlStr);
            JsonNode bizNode = rootNode.get("SECURITIES_MSGSRSV1").get("VSASIGNTRNRS");
            String s = XML_MAPPER.writeValueAsString(bizNode);
            businessResp = XML_MAPPER.readValue(s, new TypeReference<BusinessResp<SignResult>>() {});

        } catch (IOException e) {
            throw new CibException("解析异常", e);
        }

        if (businessResp == null) {
            throw new CibException("解析异常：解析结果为空");
        }

        String code = businessResp.getStatus().getCode();
        if (!"0".equals(code)) {
            throw new CibException("查询失败： 交易处理状态异常");
        }

        SignResult signResult = businessResp.getRespParams();
        if ("SUCC".equals(signResult.getResult()) || "Y".equals(signResult.getSignFlag())) {
            return true;
        }

        return false;

    }

    @Override
    public Boolean openVsa(String mainAcct, String subName, String subAcct) {
        VSAOpenInfo vsaOpenInfo = new VSAOpenInfo();
        vsaOpenInfo.setSubName(subName);
        vsaOpenInfo.setSubAcct(subAcct);
        // 以下参数固定写死
        vsaOpenInfo.setCalInterestIdent("0");
        vsaOpenInfo.setRateCode("01000000");
        vsaOpenInfo.setRateScaleFloatVal("0");
        vsaOpenInfo.setRatePointFloatVal("0");
        vsaOpenInfo.setRateWorkDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()));
        vsaOpenInfo.setSettleInterestIdent("B");
        vsaOpenInfo.setSettleInterestCycle("Y01");
        vsaOpenInfo.setFirstSettleInterestDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()));
        vsaOpenInfo.setBudgetIdent("0");
        vsaOpenInfo.setBudgetCycle("0");
        vsaOpenInfo.setBudgetQuota("0");
        vsaOpenInfo.setOverQuota("0");

        List<VSAOpenInfo> vsaOpenInfos = new ArrayList<>();
        vsaOpenInfos.add(vsaOpenInfo);

        OpenAcct openAcct = new OpenAcct();
        openAcct.setMainAcct(mainAcct);
        openAcct.setVsaOpenInfos(vsaOpenInfos);
        openAcct.setTotalCount("1");
        // BusinessResp<OpenAcctResult> result = caller.execute(openAcct, new TypeReference<BusinessResp<OpenAcctResult>>() {
        // });
        // if (result == null || result.getRespParams() == null) {
        //     return false;
        // }
        // VSAOpenInfoResult vsaOpenInfoResult = result.getRespParams().getVsaOpenInfos().get(0);
        // String status = vsaOpenInfoResult.getStatus();
        // if ("PAYOUT".equals(status)) {
        //     return true;
        // }

        String xmlStr = caller.execute(openAcct);

        BusinessResp<OpenAcctResult> businessResp = null;
        try {
            JsonNode rootNode = XML_MAPPER.readTree(xmlStr);
            JsonNode bizNode = rootNode.get("SECURITIES_MSGSRSV1").get("VSABATCHOPENTRNRS");
            String s = XML_MAPPER.writeValueAsString(bizNode);
            businessResp = XML_MAPPER.readValue(s, new TypeReference<BusinessResp<OpenAcctResult>>() {});

        } catch (IOException e) {
            // e.printStackTrace();
            throw new CibException("解析异常", e);
        }

        if (businessResp == null) {
            throw new CibException("解析异常：解析结果为空");
        }

        String code = businessResp.getStatus().getCode();
        if (!"0".equals(code)) {
            throw new CibException("查询失败： 交易处理状态异常");
        }

        VSAOpenInfoResult vsaOpenInfoResult = businessResp.getRespParams().getVsaOpenInfos().get(0);
        String status = vsaOpenInfoResult.getStatus();
        if ("PAYOUT".equals(status)) {
            return true;
        }

        return false;
    }

    @Override
    public TransferResult innerTransfer(String mainAcct, String subAcct, String toSubAcct, BigDecimal transAmt, String purpose, String memo, String requestSeqId) {
        InnerTransfer innerTransfer = new InnerTransfer();
        innerTransfer.setMainAcct(mainAcct);
        innerTransfer.setSubAcct(subAcct);
        innerTransfer.setToSubAcct(toSubAcct);
        innerTransfer.setTransAmt(transAmt);
        innerTransfer.setPurpose(purpose);
        innerTransfer.setMemo(memo);
        innerTransfer.setExpectDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now()));

        // BusinessResp<InnerTransferResult> result = caller.execute(innerTransfer, new TypeReference<BusinessResp<InnerTransferResult>>() {
        // });

        String xmlStr = caller.execute(innerTransfer);
        // caller.get()

        BusinessResp<InnerTransferResult> businessResp = null;
        try {
            JsonNode rootNode = XML_MAPPER.readTree(xmlStr);
            JsonNode bizNode = rootNode.get("SECURITIES_MSGSRSV1").get("VSAINTRSFTRNRS");
            // String s = XML_MAPPER.writeValueAsString(bizNode);
            // businessResp = XML_MAPPER.readValue(s, new TypeReference<BusinessResp<OpenAcctResult>>() {});
            JsonNode statusNode = bizNode.get("STATUS");
            Status status = XML_MAPPER.readValue(XML_MAPPER.writeValueAsString(statusNode), Status.class);
            if (!"0".equals(status.getCode())) {
                throw new CibException("查询失败： 交易处理状态异常");
            }
            JsonNode xferprcstsNode = bizNode.get("XFERPRCSTS");
            TransferProcessResult transferProcessResult = XML_MAPPER.readValue(XML_MAPPER.writeValueAsString(xferprcstsNode), TransferProcessResult.class);
            // if (!XferPrcConstants.PAYOUT.equals(transferProcessResult.getCode())) {
            //     throw new CibException("查询失败： 交易处理状态异常");
            // }

            JsonNode vsaintrsfrs = bizNode.get("VSAINTRSFRS");
            InnerTransferResult innerTransferResult = XML_MAPPER.readValue(XML_MAPPER.writeValueAsString(vsaintrsfrs), InnerTransferResult.class);

            String serviceId = innerTransferResult.getServiceId();
            TransferResult transferResult = new TransferResult();
            transferResult.setTransId(serviceId);
            transferResult.setSequence(caller.getSequence());
            transferResult.setStatus(transferProcessResult.getCode());
            transferResult.setMessage(transferProcessResult.getMessage());
            String code = transferProcessResult.getCode();
            if (XferPrcEnum.valueOf(code).ordinal() == 0) {
                // 中间状态
                transferResult.setMidStatus(true);
            } else {
                transferResult.setMidStatus(false);
            }

            return transferResult;
        } catch (IOException e) {
            // e.printStackTrace();
            throw new CibException("解析异常", e);
        } finally {
            caller.removeSequence();
        }


    }

    @Override
    public SubAcctInfo queryVsa(String mainAcct, String subAcct) {
        SubAcctQuery subAcctQuery = new SubAcctQuery();
        subAcctQuery.setMainAcct(mainAcct);
        // if (StringUtils.isBlank(subAcct)) {
        //     subAcct = "ALL";
        // }
        subAcctQuery.setSubAcct(subAcct);
        subAcctQuery.setStartRow("1");

        // BusinessResp<SubAcctQueryResult> result = caller.execute(subAcctQuery, new TypeReference<BusinessResp<SubAcctQueryResult>>() {
        // });

        String xmlStr = caller.execute(subAcctQuery);
        BusinessResp<InnerTransferResult> businessResp = null;
        try {
            JsonNode rootNode = XML_MAPPER.readTree(xmlStr);
            JsonNode bizNode = rootNode.get("SECURITIES_MSGSRSV1").get("VSASUBACCTINFOTRNRS");
            JsonNode statusNode = bizNode.get("STATUS");
            Status status = XML_MAPPER.readValue(XML_MAPPER.writeValueAsString(statusNode), Status.class);
            if (!"0".equals(status.getCode())) {
                throw new CibException("查询失败： 交易处理状态异常");
            }
            JsonNode subAccountInfoNode = bizNode.get("SUBACCTLIST").get("SUBACCTINFO");
            SubAcctInfo subAcctInfo = XML_MAPPER.readValue(XML_MAPPER.writeValueAsString(subAccountInfoNode), SubAcctInfo.class);
            return subAcctInfo;
        } catch (IOException e) {
            // e.printStackTrace();
            throw new CibException("解析异常", e);
        }

    }

    @Override
    public SubAcctTransDetailQueryResult queryTrans(String mainAcct, String subAcct) {
        SubAcctTransDetailQuery subAcctTransDetailQuery = new SubAcctTransDetailQuery();
        subAcctTransDetailQuery.setType("1");
        subAcctTransDetailQuery.setMainAcct(mainAcct);
        subAcctTransDetailQuery.setSubAcct(subAcct);

        AccountForm accountForm = new AccountForm();
        accountForm.setAcctId(mainAcct);
        subAcctTransDetailQuery.setAcctfrom(accountForm);

        String xmlStr = caller.execute(subAcctTransDetailQuery);
        BusinessResp<SubAcctTransDetailQueryResult> businessResp = null;
        try {
            JsonNode rootNode = XML_MAPPER.readTree(xmlStr);
            JsonNode bizNode = rootNode.get("SECURITIES_MSGSRSV1").get("VATSTMTTRNRS");
            JsonNode statusNode = bizNode.get("STATUS");
            Status status = XML_MAPPER.readValue(XML_MAPPER.writeValueAsString(statusNode), Status.class);
            if (!"0".equals(status.getCode())) {
                throw new CibException("查询失败： 交易处理状态异常");
            }
            JsonNode node = bizNode.get("VATSTMTRS");
            SubAcctTransDetailQueryResult subAcctTransDetailQueryResult = XML_MAPPER.readValue(XML_MAPPER.writeValueAsString(node), SubAcctTransDetailQueryResult.class);
            return subAcctTransDetailQueryResult;
        } catch (IOException e) {
            // e.printStackTrace();
            throw new CibException("解析异常", e);
        }
    }

}
