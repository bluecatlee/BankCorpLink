package com.github.bluecatlee.ccb.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface CCBApi {

//    @Headers({
//            "Connection:close",
//            "Content-Type:application/x-www-form-urlencoded",
//    })
//    @POST("/")
//    Call<ResponseBody> execute(@Header("Content-Length") int length, @Body byte[] body);

    @POST("/")
    @FormUrlEncoded
    Call<ResponseBody> execute(@HeaderMap Map<String, String> headers, @Field("requestXml") String body);

}
