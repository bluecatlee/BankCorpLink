package com.github.bluecatlee.ccb;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.bluecatlee.AppApplication;
import com.github.bluecatlee.ccb.bean.base.CCBPayRequest;
import com.github.bluecatlee.ccb.bean.base.CCBPayResponse;
import com.github.bluecatlee.ccb.bean.request.PayRequest;
import com.github.bluecatlee.ccb.redundance.BaseRequest;
import com.github.bluecatlee.ccb.redundance.BaseResponse;
import com.github.bluecatlee.ccb.redundance.PaymentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 建设银行测试
 *
 * @Date 2021/2/24 15:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppApplication.class)
@ActiveProfiles("test")
public class CCBTest {

    private static final XmlMapper XML_MAPPER = new XmlMapper();
    static {
//        XML_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        XML_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Resource(name = "ccbPayServiceImpl")
    private PaymentService paymentService;

    @Resource
    private CCBClient ccbClient;

    public static void main(String[] args) throws Exception{

        String x= "30819d300d06092a864886f70d010101050003818b0030818702818100b3edcdf695ab8c61a6ec803e8975c6b141de56e76a0deb572ac75a2aae08d5ab46571a9400f52b1667912faff2fbc51c09ab5629f9c8d85e1227faea22bd96d399c7bc14226e07d9385e6042377351c70ca2a35d77f5b4a6fbf7baf0c27b85cadbd9256ec994383d6b366eecb937e8b977aaa57b88d584dc35833395a2dae369020111";


        String substring = x.substring(x.length() - 30);


        System.out.println(substring);


//        String x = "MERCHANTID=105000354117472&POSID=054196053&BRANCHID=310000000&ORD" +
//                "ERID=19991101234&PAYMENT=0.01&CURCODE=01&TXCODE=520100&REMARK1=&REMARK2=&TYPE=1&PUB=3d1c92734f592defdeeee267020111&GAT" +
//                "EWAY=0&CLIENTIP=172.0.0.1&REGINFO=%u5C0F%u98DE%u4FA0&PROINFO=" +
//                "%u5145%u503C%u5361&REFERER=";
//
//        String md5 = MD5Utils.getMD5Code(x);
//        System.out.println(md5);
//
//        String url = "https://ibsbjstar.ccb.com.cn/CCBIS/ccbMain?"+x+"&MAC=" + md5;
//
//        System.out.println(url);


//        String x = "3d1c92734f592defdeeee267020111";
//        System.out.println(x.length());


//        String str = "MERCHANTID=105000354117472&POSID=054196053&BRANCHID=310000000&ORDERID=2231074835420444&PAYMENT=1&CURCODE=01&TXCODE=520100&REMARK1=&REMARK2=&TYPE=1&GATEWAY=&CLIENTIP=&REGINFO=&PROINFO=&REFERER=&";
//
//         String s2 = "MERCHANTID=105000354117472&POSID=054196053&BRANCHID=310000000&ORDERID=2231074835420444&PAYMENT=1&CURCODE=01&TXCODE=520100&REMARK1=&REMARK2=&TYPE=1&PUB=3d1c92734f592defdeeee267020111&GATEWAY=&CLIENTIP=&REGINFO=&PROINFO=&REFERER=";
//         String s3 = "MERCHANTID=105000354117472&POSID=054196053&BRANCHID=310000000&ORDERID=2231074835420444&PAYMENT=1&CURCODE=01&REMARK1=&REMARK2=&TXCODE=520100&MAC=69b1b6a072b953448a91690cd8a26416&TYPE=1&GATEWAY=0&REFERER=";
//        String result = MD5Utils.getMD5Code(str);
//        String result2 = MD5Utils.getMD5Code(s2);
//        System.out.println(result);
//        System.out.println(result2);


//        String test = "<?xml version=\"1.0\" encoding=\"GB18030\"?>\n" +
//                "<TX>\n" +
//                "    <REQUEST_SN>2104120090001</REQUEST_SN>\n" +
//                "    <RETURN_CODE>YDCA02910001</RETURN_CODE>\n" +
//                "    <RETURN_MSG>��ˮ��¼������</RETURN_MSG>\n" +
//                "</TX>";
//
//        //        String gbk = new String(test.getBytes("GB18030"));
////        System.out.println(gbk);
//
//        String origi = "<?xml version=\"1.0\" encoding=\"GB18030\"?>\n" +
//                "<TX>\t\t\t\t<REQUEST_SN>2104120090001</REQUEST_SN>\t  \t  \t  \t\t  \t\t\t\t\t\t\t<RETURN_CODE>YDCA02910001</RETURN_CODE>\t\t\t\t\t  \t  \t<RETURN_MSG>流水记录不存在</RETURN_MSG></TX>";
//
//        String gb18030 = new String(origi.getBytes("GB18030"));
//        System.out.println(gb18030);
//
//        System.out.println(Arrays.toString(origi.getBytes("GB18030")));
//        System.out.println(Arrays.toString(gb18030.getBytes()));
//
//
//        String nnnnn = new String(gb18030.getBytes(),"GB18030");
//        System.out.println(nnnnn);

    }

    @Test
    public void calMac() {
        PayRequest req = new PayRequest();
        req.setMerchantId("105000354117472");
        req.setPosId("054196053");
        req.setBranchId("310000000");
        req.setOrderId("2231074835420444");
        req.setPayment("1");
        req.setCurCode("01");
        req.setTxCode("520100");
        req.setType("1");
        String s = null;
        try {
            s = ccbClient.calMac(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }

    @Test
    public void testPay() {
        CCBPayRequest request = new CCBPayRequest();
        CCBPayResponse response = new CCBPayResponse();

        request.setOutTradeNo("902371034520447750_1");
        request.setTotalFee("1750");           // 分
        request.setTradeName("11111111111");
        request.setUsrNumId("2004230000117750");
        request.setSubUnitNumId("100042");

        try {
            BaseResponse result = paymentService.pay(request, response);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryPay() {
        CCBPayRequest request = new CCBPayRequest();
        CCBPayResponse response = new CCBPayResponse();
        request.setOutTradeNo("902111094420984750_1");
        try {
            BaseResponse reult = paymentService.queryPayResult(request, response);
            System.out.println(reult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRefund() {
        CCBPayRequest request = new CCBPayRequest();
        CCBPayResponse response = new CCBPayResponse();

        request.setOutTradeNo("REFUND902741034720874121_1");
        request.setTotalFee("150");       // 分
        request.setSrcOutTradeNo("902741034720874121_1");
        request.setPlatType("86");

        try {
            BaseResponse result = paymentService.refund(request, response);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryRefund() {
        CCBPayRequest request = new CCBPayRequest();
        CCBPayResponse response = new CCBPayResponse();

        request.setSrcOutTradeNo("902341054720859121_1");

        try {
            BaseResponse result = paymentService.queryRefundResult(request, response);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testNotify() {
        BaseRequest request = new BaseRequest();
        BaseResponse response = new BaseResponse();
        request.setBody("POSID=054196053&BRANCHID=310000000&ORDERID=2231074835420453&PAYMENT=0.01&CURCODE=01&REMARK1=&REMARK2=&ACC_TYPE=02&SUCCESS=Y&TYPE=1&REFERER=&CLIENTIP=101.84.6.178&SIGN=6651176c81704fd9e100fea2be1c6fcdcf44898a47e4a56ab470b36b294cea7445098d0c57cac329e1d3ee758f12bda4fb13b94e6bbf75db26caa8c6284f2bdb4ad5e6c2a17b139910ad3bb88a9c5c762e7963b798072e072f869a746da2d19e940670869bc8a57bacd3fbcba757a0ef838394c6ddad1f5c2ca0ee3a3b0b707a");

        try {
            BaseResponse baseResponse = paymentService.callbackNotify(request, response);
            System.out.println(baseResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
