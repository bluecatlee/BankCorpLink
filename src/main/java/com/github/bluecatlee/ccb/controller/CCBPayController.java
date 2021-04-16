package com.github.bluecatlee.ccb.controller;

import com.github.bluecatlee.ccb.CCBClient;
import com.github.bluecatlee.ccb.bean.base.CCBPayRequest;
import com.github.bluecatlee.ccb.bean.base.CCBPayResponse;
import com.github.bluecatlee.ccb.redundance.BaseResponse;
import com.github.bluecatlee.ccb.redundance.RespEnum;
import com.github.bluecatlee.ccb.service.impl.CCBPayServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 提供方便查询的接口
 * @Date 2021/2/24 15:47
 */
@RestController
@RequestMapping("/ccbpay")
@Api(tags = "建行支付")
public class CCBPayController {

    @Resource
    private CCBClient client;

    @Resource
    private CCBPayServiceImpl ccbPayService;

    @ApiOperation(value = "测试外联平台客户端是否可以正常连接")
    @GetMapping("/testConnect")
    public BaseResponse testConnect() {
        BaseResponse baseResponse = new BaseResponse();
        try {
            client.connect();
            baseResponse.setCode(RespEnum.SUCCESS.getStatus());
            baseResponse.setMessage("连接成功");
        } catch (Exception e) {
            baseResponse.setCode(RespEnum.FAIL.getStatus());
            baseResponse.setMessage(e.getMessage());
        }
        return baseResponse;
    }

    @ApiOperation(value = "查询支付结果")
    @GetMapping("/queryPayResult")
    public BaseResponse queryPayResult(String outTradeNo) {

        CCBPayRequest request = new CCBPayRequest();
        CCBPayResponse response = new CCBPayResponse();
        request.setOutTradeNo(outTradeNo);

        try {
            ccbPayService.queryPayResult(request, response);
        } catch (Exception e) {
            response.setCode(RespEnum.FAIL.getStatus());
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "查询退款结果")
    @GetMapping("/queryRefundResult")
    public BaseResponse queryRefundResult(String srcOutTradeNo) {

        CCBPayRequest request = new CCBPayRequest();
        CCBPayResponse response = new CCBPayResponse();
        request.setOutTradeNo(srcOutTradeNo);

        try {
            ccbPayService.queryRefundResult(request, response);
        } catch (Exception e) {
            response.setCode(RespEnum.FAIL.getStatus());
            response.setMessage(e.getMessage());
        }
        return response;
    }

}
