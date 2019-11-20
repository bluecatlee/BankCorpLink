package com.github.bluecatlee.bcm.valid;

import com.github.bluecatlee.bcm.annotation.BcmField;
import com.github.bluecatlee.bcm.exception.BcmException;
import com.github.bluecatlee.bcm.request.BcmReqParam;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * bean校验
 */
public class Validator {

    public static void validate(BcmReqParam... params) throws IllegalAccessException {
        for (BcmReqParam param : params) {
            Class<? extends BcmReqParam> clazz = param.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                BcmField annotation = field.getAnnotation(BcmField.class);
                if (annotation == null) {
                    continue;
                }
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                Object val = field.get(param);
                // 校验必填
                if (annotation.required()) {
                    if (val == null) {
                        throw new BcmException(String.format("Required parameter [%s] is not present.", field.getName()));
                    }
                }
                // 校验普通字符串数据长度
                if (annotation.slen() > 0 && val != null) {
                    if (val instanceof String) {
                        if (((String) val).length() > annotation.slen()) {
                            throw new BcmException(String.format("Required string parameter [%s] exceed max length.", field.getName()));
                        }
                    }
                }
                // 校验数值长度精度 支持String和BigDecimal类型表示的数值 不支持负数
                if (annotation.dlen() > 0 && val != null) {
                    int integrelLength;
                    BigDecimal digitVal = null;
                    if (val instanceof String) {
                        digitVal = new BigDecimal((String) val);
                    } else if (val instanceof BigDecimal) {
                        digitVal = (BigDecimal) val;
                    } else if (val instanceof Integer) {
                        digitVal = new BigDecimal((Integer)val);
                    } else if (val instanceof Long) {
                        digitVal = new BigDecimal((Long)val);
                    }
                    integrelLength = digitVal.setScale(0, BigDecimal.ROUND_DOWN).toString().length();
                    if (integrelLength > annotation.dlen()) {
                        throw new BcmException(String.format("Required digit parameter [%s] exceed max integrel length.", field.getName()));
                    }
                    if (annotation.plen() == 0) {
                        int scale = digitVal.scale();
                        if (scale > 0) {
                            throw new BcmException(String.format("Required digit parameter [%s] exceed max decimal length.", field.getName()));
                        }
                    }
                }
                if (annotation.plen() > 0 && val != null) {
                    int scale = 0;
                    if (val instanceof String) {
                        String strVal = (String) val;
                        BigDecimal bigDecimal = new BigDecimal(strVal);
                        scale = bigDecimal.scale();
                    } else if (val instanceof BigDecimal) {
                        BigDecimal digitVal = (BigDecimal) val;
                        scale = digitVal.scale();
                    }
                    if (scale > annotation.plen()) {
                        throw new BcmException(String.format("Required digit parameter [%s] exceed max decimal length.", field.getName()));
                    }
                }
                field.setAccessible(accessible);
            }
        }
    }

}
