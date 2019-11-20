package com.github.bluecatlee.bcm.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BcmField {

    /*
    String HEAD = "head";

    String BODY = "body";

    enum Tag {
        Head(HEAD), Body(BODY);

        String value;

        Tag(String value) {
            this.value = value;
        }
    }

    Tag tag() default Tag.Body;

    boolean isMfs default false;
    */

    boolean required() default false;

    // 普通字符串最大长度(默认值-1表示无限制)
    int slen() default -1;

    // 数值字符串整数部分最大长度
    int dlen() default -1;

    // 数值字符串小数部分最大长度
    int plen() default 0;

}