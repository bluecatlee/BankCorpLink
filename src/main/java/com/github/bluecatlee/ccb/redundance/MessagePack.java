package com.github.bluecatlee.ccb.redundance;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy.LowerCaseStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@JsonPropertyOrder({"code", "message"})
public class MessagePack implements Serializable {
    private static final long serialVersionUID = -6582589288347118121L;
    private long code;
    private String message = "成功";
    public static long OK = 0L;
    public static long EXCEPTION = -1L;
    private String fullMessage = null;

    public MessagePack() {
    }

    public long getCode() {
        return this.code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFullMessage() {
        return this.fullMessage;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.ALWAYS);

        try {
            return mapper.writeValueAsString(this);
        } catch (IOException var3) {
            System.out.println("write to json string error:" + var3.getMessage());
            var3.printStackTrace();
            return null;
        }
    }

    public String toLowerCaseJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.ALWAYS);
        mapper.setPropertyNamingStrategy(LowerCaseStrategy.SNAKE_CASE);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        try {
            return mapper.writeValueAsString(this);
        } catch (IOException var4) {
            var4.printStackTrace();
            System.out.println("write to json string error:" + var4.getMessage());
            return null;
        }
    }
}

