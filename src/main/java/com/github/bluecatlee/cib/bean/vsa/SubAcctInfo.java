package com.github.bluecatlee.cib.bean.vsa;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class SubAcctInfo {

    /**
     * 18位主账户
     */
    @JacksonXmlProperty(localName = "MAINACCT")
    private String mainAcct;

    /**
     * 子账户
     */
    @JacksonXmlProperty(localName = "SUBACCT")
    private String subAcct;

    /**
     * 子账户名
     */
    @JacksonXmlProperty(localName = "SUBACCTNAME")
    private String subAcctName;

    /**
     * 子账户利率
     */
    @JacksonXmlProperty(localName = "RATE")
    private String rate;

    /**
     * 子账户余额
     */
    @JacksonXmlProperty(localName = "BALANCE")
    private String balance;

    /**
     * 子账户累计积数
     */
    @JacksonXmlProperty(localName = "SUMMED")
    private String summed;

    /**
     * 子账户利息
     */
    @JacksonXmlProperty(localName = "INTEREST")
    private String interest;

    /**
     * 子账户状态，
     *      0-未授权，1-激活，2-可归档
     */
    @JacksonXmlProperty(localName = "STATUS")
    private String status;

    /*
    * 查询模式为1时响应增加详细信息：
    * */
    /**
     *  利率代号
     *      01010000-单位活期
     *      01021D01-单位一天通知
     *      01021D07-单位7天通知
     *      01030M03-单位定期3个月
     *      01030M06-单位定期6个月
     *      01030Y01-单位定期1年
     *      01030Y02-单位定期2年
     *      01030Y03-单位定期3年
     *      01030Y05-单位定期5年
     */
    @JacksonXmlProperty(localName = "RATECODE")
    private String rateCode;

    /**
     *  利率生效日期，格式YYYY-MM-DD
     */
    @JacksonXmlProperty(localName = "RATEWORKDATE")
    private String rateWorkDate;

    /**
     *  利率比例浮动值
     */
    @JacksonXmlProperty(localName = "RATESCALEFLTVAL")
    private String rateScaleFloatVal;

    /**
     *  利率点数浮动值
     */
    @JacksonXmlProperty(localName = "RATEPOINTFLTVAL")
    private String ratePointFloatVal;

    /**
     *  执行利率
     */
    @JacksonXmlProperty(localName = "EXECRATE")
    private String execRate;

    /**
     *  计息标志
     *      0-不计息
     *      1-不分段计息
     *      2-按日分段计息
     */
    @JacksonXmlProperty(localName = "CALINTSIDENT")
    private String calInterestIdent;

    /**
     *  结息标志(即计息周期)
     *      0-按月结息
     *      1-按季结息
     *      2-按年结息
     *      3-利随本清
     *      4-手工结息
     *      7-按日结息
     *      8-满月结息
     *      9-满季结息
     *      A-满半年结息
     *      B-满一年结息
     *      C-满周结息
     *      D-满10天结息
     *      E-按半年结息
     *      F-自定义周期结息
     */
    @JacksonXmlProperty(localName = "ADJINTSIDENT")
    private String settleInterestIdent;

    /**
     *  结息周期
     *      当结息标志为3、4时为D00；
     *      当结息标志为7时为D01；
     *      当结息标志为C时为D07；
     *      当结息标志为D时为D10；
     *      当结息标志为0、8时为M01；
     *      当结息标志为1、9时为M03；
     *      当结息标志为E、A时为M06；
     *      当结息标志为2、B时为Y01；
     *      当结息标志为F时，为客户手工录入数值。
     */
    @JacksonXmlProperty(localName = "ADJINTSCYCLE")
    private String settleInterestCycle;

    /**
     *  初次结息日期，格式YYYY-MM-DD
     */
    @JacksonXmlProperty(localName = "FIRADJINTSDATE")
    private String firstSettleInterestDate;

    /**
     *  应加应减积数
     */
    @JacksonXmlProperty(localName = "ADDREDMED")
    private String addRedMed;

    /**
     *  未结利息
     */
    @JacksonXmlProperty(localName = "NOKOTINTS")
    private String unSettledInterest;

    /**
     *  预算标志，0-否 1-是
     */
    @JacksonXmlProperty(localName = "BUDGETIDENT")
    private String budgetIdent;

    /**
     *  预算额度
     */
    @JacksonXmlProperty(localName = "BUDGETQUOTA")
    private String budgetQuota;

    /**
     *  预算额度起始日，
     *      格式YYYY-MM-DD
     */
    @JacksonXmlProperty(localName = "BUDGETDTSTART")
    private String budgetDateStart;

    /**
     *  预算额度到期日，
     *      格式YYYY-MM-DD
     */
    @JacksonXmlProperty(localName = "BUDGETDTEND")
    private String budgetDateEnd;

    /**
     *  额度循环标志：0-不循环，1-日，3-月，4-季，5-半年，6-年
     */
    @JacksonXmlProperty(localName = "BUDGETCYCLE")
    private String budgetCycle;

    /**
     *  透支额度，大于0表示可透支
     */
    @JacksonXmlProperty(localName = "OVERQUOTA")
    private String overQuota;

    /**
     *  结算账号
     */
    @JacksonXmlProperty(localName = "ACCTID")
    private String acctId;

    /**
     *  主账户户名
     */
    @JacksonXmlProperty(localName = "ACCTNAME")
    private String acctName;

    /**
     *  主账户余额
     */
    @JacksonXmlProperty(localName = "ACCTBAL")
    private String acctBalance;

    /**
     *  子账户汇总余额
     */
    @JacksonXmlProperty(localName = "SUBSUMBAL")
    private String subSumBalance;

}
