package com.github.bluecatlee.cib.service;

import com.github.bluecatlee.cib.bean.TransferResult;
import com.github.bluecatlee.cib.bean.accquery.response.AccountQueryResult;
import com.github.bluecatlee.cib.bean.vsa.SubAcctInfo;
import com.github.bluecatlee.cib.bean.vsa.result.SubAcctTransDetailQueryResult;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

/**
 * 银行业务service
 *      交易类接口如果异常需要查询交易结果 不要重新发起
 */
public interface CibService {

    /**
     * 查询账户信息
     * @param mainAcct 18位主账号
     */
    AccountQueryResult queryAccount(String mainAcct);

    /**
     * 虚拟主账户签约
     * @param mainAcct 18位主账号
     */
    Boolean vsaSign(String mainAcct);

    /**
     * 虚拟子账户开户
     * @param mainAcct 18位主账号
     * @param subName 子账号户名
     * @param subAcct 子账号
     */
    Boolean openVsa(String mainAcct, String subName, String subAcct);

    /**
     * 虚拟子账户内部转账
     * @param mainAcct  18位主账号
     * @param subAcct   付款方
     * @param toSubAcct 收款方
     * @param transAmt  转账金额 Decimal(17,2)
     * @param purpose 转账用途  支付服务费/退还服务费
     * @param memo 备注
     * @param requestSeqId 发起方请求序号 重试时使用(此时相当于查询)
     */
    TransferResult innerTransfer(String mainAcct, String subAcct, String toSubAcct, BigDecimal transAmt, String purpose, @Nullable String memo, @Nullable String requestSeqId);

    /**
     * 查询子账号信息
     * @param mainAcct 主账号
     * @param subAcct 子账号
     */
    SubAcctInfo queryVsa(String mainAcct, String subAcct);

    /**
     * 查询交易记录 todo
     *      如果不传查询日期 则是查询余额(总账户余额)
     * @param mainAcct
     * @param subAcct
     * @return
     */
    SubAcctTransDetailQueryResult queryTrans(String mainAcct, String subAcct);

}
