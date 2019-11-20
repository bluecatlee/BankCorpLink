package com.github.bluecatlee.bcm.service;


import com.github.bluecatlee.bcm.bean.collection.BatchCollec;
import com.github.bluecatlee.bcm.bean.collection.BatchCollecDetail;
import com.github.bluecatlee.bcm.bean.collection.QueryBatchCollecReq;
import com.github.bluecatlee.bcm.bean.info.AccountInfo;
import com.github.bluecatlee.bcm.bean.protocol.QuerySubProtocolReq2;
import com.github.bluecatlee.bcm.bean.protocol.SubProtocol;

import java.util.List;

public interface BcmService {

    /**
     * 查询单个账户信息
     * @param accountNo
     * @return
     */
    AccountInfo queryAccountInfo(String accountNo);

    /**
     * 批量查询账户信息
     * @param accountNos
     * @return
     */
    List<AccountInfo> queryAccountInfo(String[] accountNos);

    /**
     * 子协议查询 调用者校验子协议有效期及额度
     * @param cagrNo 收款协议编号
     * @return
     */
    @Deprecated
    List<SubProtocol> querySubProtocol(String cagrNo);

    /**
     * 子协议查询
     * @param query 查询参数
     * @return
     */
    List<SubProtocol> querySubProtocol(QuerySubProtocolReq2 query);

    /**
     * 子协议查询
     * @param cagrNo 收款协议编号
     * @param accNo 付款账号（19位卡号或21位账号）
     * @param payAcname 收款户名
     * @return
     */
    SubProtocol querySubProtocol(String cagrNo, String accNo, String payAcname);

    /**
     * 批量代收 调用者处理多域串参数
     * @param batchCollec
     * @return 网银批次号
     */
    String batchCollection(BatchCollec batchCollec);

    /**
     * 批量代收查询
     * @param flwno 流水号
     * @return
     */
    List<BatchCollecDetail> queryCollectionByFlwno(String flwno);

    /**
     * 批量代收查询
     * @param dealNo 企业凭证号
     * @return
     */
    List<BatchCollecDetail> queryCollectionByDealNo(String dealNo);

    /**
     * 批量代收查询
     * @param query
     * @return
     */
    List<BatchCollecDetail> queryCollection(QueryBatchCollecReq query);

}
