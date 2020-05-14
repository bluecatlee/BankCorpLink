package com.github.bluecatlee.cib.bean.accquery.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.bluecatlee.cib.base.response.RespParams;
import lombok.Data;

/**
 * 账户信息查询
 */
@Data
public class AccountQueryResult extends RespParams {

    /**
     * 账户代号 最长32位
     */
    @JsonProperty("ACCTID")
    private String acctId;

    /**
     * 账户名称 最长60位
     */
    @JsonProperty("NAME")
    private String name;

    /**
     * 开户人组织机构代码证，最小1位,最大10位（非必回）
     */
    @JsonProperty("ORGID")
    private String orgId;

    /**
     * 账户类型，2位小数
     *  0-一般户，
     *  1-基本户，
     *  2-临时户，
     *  3-专用户，
     *  4-纳税户，
     *  5-个人存款户,
     *  9-其它,
     *  a-经常项目外汇结算户,
     *  b-经常项目外汇专用户,
     *  c-资本项目资本金户,
     *  d-资本项目外汇贷款专户,
     *  e-资本项目外汇还本付息专户,
     *  f-外债(外债转贷款)专户,
     *  g-资本项目外汇专用户,
     *  n-银行外汇存款户,
     *  o-非银行金融机构外汇存款户,
     *  p-境外银行外汇存款户,
     *  q-境外非银行金融机构外汇存款户,
     *  r-证券公司B股资金清算户,
     *  s-证券公司外汇资本金户,
     *  t-证券公司外汇自有资金户,
     *  u-证券公司客户交易结算资金专用户
     */
    @JsonProperty("ACCTTYPE")
    private String acctType;

    /**
     * 开户行名称，最大60位（非必回）
     */
    @JsonProperty("BANKDESC")
    private String bankDesc;

    /**
     * 开户行地址 （非必回）
     */
    @JsonProperty("BANKADDR")
    private String bankAddr;

    /**
     * 币种,2位，参考附录货币代号说明
     */
    @JsonProperty("CURRENCY")
    private String currency;

    /**
     * 是否冻结
     *      1-有效，
     *      2-销户，
     *      3-挂失，
     *      4-冻结，
     *      5-不动户，
     *      7-已入账，
     *      8-销账未成功（出付），
     *      9-退票（出付）
     *      A-结清，
     *      B-控制，
     *      C-抵押，
     *      D-回收，
     *      F-协防，
     *      G-公示催告,
     *      H-久悬，
     *      I-开户暂封
     */
    @JsonProperty("ISFREEZE")
    private String isFreeze;

    /**
     * 是否归集 (0-归集 1-不归集)
     */
    @JsonProperty("ISGATHER")
    private String isGather;

    /**
     * 人行联行号（非必回）
     */
    @JsonProperty("RSLTID")
    private String rsltId;

    /**
     * 开户日期YYYY-MM-DD
     */
    @JsonProperty("OPENDATE")
    private String openDate;

    /**
     * 账户实时余额
     */
    @JsonProperty("BALAREAL")
    private String realBalance;

    /**
     * 账户可用余额
     */
    @JsonProperty("BALAMT")
    private String availBalance;

}
