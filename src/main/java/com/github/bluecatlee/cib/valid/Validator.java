package com.github.bluecatlee.cib.valid;

import com.github.bluecatlee.cib.base.request.ReqParams;
import com.github.bluecatlee.cib.exception.CibException;
import com.github.bluecatlee.cib.valid.annotation.CibField;

import java.lang.reflect.Field;

/**
 * bean校验
 */
public class Validator {

    public static void validate(ReqParams param) {
        if (param == null) {
            throw new CibException("Intput object param cannot be null when building xml request");
        }
        Class<? extends ReqParams> clazz = param.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            CibField annotation = field.getAnnotation(CibField.class);
            if (annotation == null) {
                continue;
            }
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object val = null;
            try {
                val = field.get(param);
            } catch (IllegalAccessException e) {
                throw new CibException(e);
            }
            // 校验必填
            if (annotation.required()) {
                if (val == null) {
                    throw new CibException(String.format("Required parameter [%s] is not present.", field.getName()));
                }
            }
            field.setAccessible(accessible);
        }
    }

}
