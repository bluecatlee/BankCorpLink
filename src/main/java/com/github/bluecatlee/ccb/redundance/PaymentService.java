package com.github.bluecatlee.ccb.redundance;

import java.util.Map;

public interface PaymentService<T extends BaseRequest, U extends BaseResponse> {

    void checkInputParams(T req, U res, String requestMethod) throws Exception;

    BaseResponse pay(T req, U res) throws Exception;

    Map afterPay(T req, String res, long id) throws Exception;

    BaseResponse refund(T req, U res) throws Exception;

    Map afterRefund(T req, String res, long id) throws Exception;

    BaseResponse queryPayResult(T req, U res) throws Exception;

    BaseResponse queryRefundResult(T req, U res) throws Exception;

    default BaseResponse callbackNotify(BaseRequest request, BaseResponse response) throws Exception {
        return null;
    }
}
