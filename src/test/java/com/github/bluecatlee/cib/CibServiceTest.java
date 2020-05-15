package com.github.bluecatlee.cib;

import com.github.bluecatlee.cib.service.CibService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestCibApplication.class)
// @ActiveProfiles("test")
public class CibServiceTest {

    // @Autowired
    // private Caller caller;

    @Autowired
    private CibService cibService;

    // @Test
    // public void testAccountQuery() {
    //     AccountQuery accountQuery = new AccountQuery();
    //     accountQuery.setAcctid("117010100100000177");
    //     BusinessResp<AccountQueryResult> execute = caller.execute(accountQuery, new TypeReference<BusinessResp<AccountQueryResult>>() {
    //     });
    //     System.out.println("======================");
    // }

    // @Test
    // public void testSubAccountQuery() {
    //     SubAcctQuery subAcctQuery = new SubAcctQuery();
    //     subAcctQuery.setMainAcct("main");
    //     subAcctQuery.setSubAcct("sub");
    //     subAcctQuery.setStartRow("1");
    //     // caller.execute(subAcctQuery);
    // }

    @Test
    public void testqueryaccount() {
        cibService.queryAccount("117010100100000177");
    }

    @Test
    public void testvsasign() {
        cibService.vsaSign("117010100100000177");
    }

    @Test
    public void testopenvsa() {
        cibService.openVsa("117010100100000177", "耶购测试子账户2", "019002");
    }

    @Test
    public void testtime() {
        String format = DateTimeFormatter.ofPattern("yyyy-MM-21").format(LocalDateTime.now());
        System.out.println(format);
    }

    @Test
    public void testtransfer() {
        // cibService.innerTransfer("117010100100000177", "019000", "019001", new BigDecimal("0.01"), "支付服务费", "");
        cibService.innerTransfer("117010100100050880", "961208", "010488", new BigDecimal("0.01"), "支付服务费", "", null);
    }

    /**
     * 019000 019001 019002
     */
    @Test
    public void testqueryVsa() {
        cibService.queryVsa("117010100100000177", "019001");
        // cibService.queryVsa("117010100100050880", "961208");
    }

    @Test
    public void testqueryTrans() {
        // cibService.queryTrans("117010100100000177", "019002");
        // cibService.queryTrans("117010100100050880", "961208");
        cibService.queryTrans("117010100100050880", "010488");
    }

}
