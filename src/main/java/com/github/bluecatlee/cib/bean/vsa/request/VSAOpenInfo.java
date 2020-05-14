package com.github.bluecatlee.cib.bean.vsa.request;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.github.bluecatlee.cib.valid.annotation.CibField;
import lombok.Data;

@Data
public class VSAOpenInfo {

    /**
     *  子账户，必须为6位数字且在010000-999989之间
     */
    @JacksonXmlProperty(localName = "SUBACCT")
    @CibField(required = true)
    private String subAcct;

    /**
     *  子账户名称
     */
    @JacksonXmlProperty(localName = "SUBNAME")
    @CibField(required = true)
    private String subName;

    /**
     *  计息标志
     *      0-不计息 1-不分段计息 2-按日分段计息
     */
    @JacksonXmlProperty(localName = "CALINTSIDENT")
    @CibField(required = true)
    private String calInterestIdent;

    /**
     *  利率代号
     *      01000000-人民币零利率
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
    @CibField(required = true)
    private String rateCode;

    /**
     *  利率比例浮动值
     *      取值范围为：
     *      （-9999.9999999~9999.9999999）
     */
    @JacksonXmlProperty(localName = "RATESCALEFLTVAL")
    @CibField(required = true)
    private String rateScaleFloatVal;

    /**
     * 利率点数浮动值
     *      取值范围为：
     *      （-99.9999999~99.9999999）
     */
    @JacksonXmlProperty(localName = "RATEPOINTFLTVAL")
    @CibField(required = true)
    private String ratePointFloatVal;

    /**
     *  利率生效日期，格式YYYY-MM-DD
     *      默认为办理业务当日，不可修改
     */
    @JacksonXmlProperty(localName = "RATEWORKDATE")
    @CibField(required = true)
    private String rateWorkDate;

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
    @CibField(required = true)
    private String settleInterestIdent;

    /**
     *  结息周期
     *      当结息标志为3、4只能为D00；
     *      当结息标志为7时，只能为D01；
     *      当结息标志为C时，只能为D07；
     *      当结息标志为D时，只能为D10；
     *      当结息标志为0、8时，只能为M01；
     *      当结息标志为1、9时，只能为M03；
     *      当结息标志为E、A时，只能为M06；
     *      当结息标志为2、B时，只能为Y01；
     *      当结息标志为F时，允许手工录入数值。
     *      手工录入的自定义结息周期必须为3位业务期限类数值（其中第一位日、月、年英文单词首字母大写加两位阿拉伯数字组成；如六天表示为D06；一个月为M01；四年表示为Y04等）。
     */
    @JacksonXmlProperty(localName = "ADJINTSCYCLE")
    @CibField(required = true)
    private String settleInterestCycle;

    /**
     *  初次结息日期，格式YYYY-MM-DD
     *      当结息标志为0默认为起始日期后最近的一个月末21日，不能修改；
     *      当结息标志为1默认为起始日期后最近的一个季度末21日，不能修改；
     *      当结息标志为E默认为起始日期后最近的一个半年末21日，不能修改；
     *      当结息标志为2默认为起始日期后最近的一个年末21日，不能修改；
     *      当结息标志为4、7、8、9、A、B、C、D默认为起始日期，不能修改；
     *      当结息标志为3默认为开户日期；
     *      当结息标志为F默认为起始日期，可修改，但必须大于等于起始日期，小于到期日期。
     */
    @JacksonXmlProperty(localName = "FIRADJINTSDATE")
    @CibField(required = true)
    private String firstSettleInterestDate;

    /**
     *  预算标志,0-否 1-是
     */
    @JacksonXmlProperty(localName = "BUDGETIDENT")
    @CibField(required = true)
    private String budgetIdent;

    /**
     *  预算额度，不可填负数
     */
    @JacksonXmlProperty(localName = "BUDGETQUOTA")
    @CibField(required = true)
    private String budgetQuota;

    /**
     *  额度循环标志：0-不循环，1-日，3-月，4-季，5-半年，6-年
     */
    @JacksonXmlProperty(localName = "BUDGETCYCLE")
    @CibField(required = true)
    private String budgetCycle;

    /**
     *  透支额度，不可填负数，
     *      大于0表示可透支
     */
    @JacksonXmlProperty(localName = "OVERQUOTA")
    @CibField(required = true)
    private String overQuota;

}
