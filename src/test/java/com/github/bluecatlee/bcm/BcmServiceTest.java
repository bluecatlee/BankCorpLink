package com.github.bluecatlee.bcm;

import com.github.bluecatlee.bcm.bean.collection.BatchCollec;
import com.github.bluecatlee.bcm.bean.collection.BatchCollecDetail;
import com.github.bluecatlee.bcm.bean.info.AccountInfo;
import com.github.bluecatlee.bcm.bean.protocol.SubProtocol;
import com.github.bluecatlee.bcm.service.BcmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestBcmApplication.class)
// @ActiveProfiles("test")
public class BcmServiceTest {

    @Autowired
    private BcmService bcmService;

    @Test
    public void testSplit() {
        System.out.println("|".split("[|]").length);  //0
        System.out.println("|".split("\\|").length);  //0
        System.out.println("||".split("[|]").length); //0
        System.out.println("||".split("\\|").length); //0

        System.out.println("1|".split("[|]").length);  //1
        System.out.println("1||".split("[|]").length); //1
        System.out.println("|1".split("[|]").length);  //2

        System.out.println("1||1".split("[|]").length); //3
    }

    @Test
    public void testQueryAccountInfo() {
        // 单个账号查询
        AccountInfo accountInfo = bcmService.queryAccountInfo("310899991010008776083");
        System.out.println(accountInfo);

        // 批量查询账号
        // List<AccountInfo> accountInfos = bcmService.queryAccountInfo(new String[]{"310899991010008776083", "310899991140008173453"});
        // System.out.println(accountInfos);
    }

    @Test
    public void testQuerySubProtocolByCagrNo() {
        // 根据主协议编号查询所有子协议
        // List<SubProtocol> subProtocols1 = bcmService.querySubProtocol("01156850036680380001");
        // System.out.println(subProtocols1);

        // 根据主协议编号 付款账号 付款户名查询子协议
        // QuerySubProtocolReq2 req = new QuerySubProtocolReq2();
        // req.setCagrNo("01156850036680380001");
        // req.setAccName("交行客");
        // List<SubProtocol> subProtocols = bcmService.querySubProtocol(req);
        // System.out.println(subProtocols);

        SubProtocol subProtocol = bcmService.querySubProtocol("01156850036680380001", "310899991010008776159", null);
        // SubProtocol subProtocol = bcmService.querySubProtocol("01156850036680380001", null, "自动化测试户变更新户名");
        System.out.println(subProtocol);
    }

    @Test
    public void testBatchCollection() {
        long time = System.currentTimeMillis();
        BatchCollec batchCollec = BatchCollec.builder()
                .dealNo(String.valueOf(time))                // 企业凭证号
                .cagrNo("01156850036680380001")              // 收款协议编号
                .recAccNo("310899991010008776083")           // 收款账号
                .recAccName("01310150999自动化B5467872")     // 收款户名

                // .totalNum("1").batchSum("1").offset("0")    // 报文总笔数 批次总笔数 偏移量
                // .totalAmt("0.01").batchSumAmt("0.01")       // 报文总金额 批次总金额
                // .subAgrNo("011568500366803800010000005")    // 付款签约编号
                // .payAccNo("310899991010008776159")          // 付款账号
                // .payAccName("自动化测试户变更新户名")       // 付款账号户名
                // .feeName("缴费一")                          // 缴费户名
                // .chgmod("0")                                // 收款方式
                // .usrdefineValue("")                         // 自定义值
                // .tranAmt("0.01")                            // 收款金额
                // .rem("")                                    // 备注

                // .totalNum("1").batchSum("1").offset("0")    // 报文总笔数 批次总笔数 偏移量
                // .totalAmt("0.01").batchSumAmt("0.01")       // 报文总金额 批次总金额
                // .subAgrNo("011568500366803800010000001")    // 付款签约编号
                // .payAccNo("6222600110063392976")            // 付款账号
                // .payAccName("交行客户")                     // 付款账号户名
                // .feeName("交行客户")                        // 缴费户名
                // .chgmod("0")                                // 收款方式
                // .usrdefineValue("交行客户")                 // 自定义值
                // .tranAmt("0.01")                            // 收款金额
                // .rem("")                                    // 备注


                /*批量*/
                .totalNum("2").batchSum("2").offset("0")    // 报文总笔数 批次总笔数 偏移量
                .totalAmt("0.02").batchSumAmt("0.02")       // 报文总金额 批次总金额
                .subAgrNo("011568500366803800010000001|011568500366803800010000005")    // 付款签约编号
                .payAccNo("6222600110063392976|310899991010008776159")                // 付款账号
                .payAccName("交行客户|自动化测试户变更新户名")                     // 付款账号户名
                .feeName("交行客户|缴费一")                        // 缴费户名
                .chgmod("0|0")                                     // 收款方式
                .usrdefineValue("|")                               // 自定义值
                .tranAmt("0.01|0.01")                              // 收款金额
                .rem("|")                                          // 备注

                .build();

        String s = bcmService.batchCollection(batchCollec);
        System.out.println("批量代收网银批次号：" + s);
    }

    @Test
    public void testQueryCollection() {
        List<BatchCollecDetail> batchCollecDetails = bcmService.queryCollectionByFlwno("2011000420350801154452414253");
        System.out.println(batchCollecDetails);

        // List<BatchCollecDetail> batchCollecDetails1 = bcmService.queryCollectionByDealNo("1572244814286");
        // System.out.println(batchCollecDetails1);

    }

}
