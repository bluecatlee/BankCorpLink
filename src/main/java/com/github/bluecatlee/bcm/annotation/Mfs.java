package com.github.bluecatlee.bcm.annotation;

import java.lang.annotation.*;

/**
 * 域注解
 *  支持String Integer Long Double BigDecimal int long java.util.Date类型的自动映射
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Mfs {

    /**
     * 域(字段)顺序
     */
    int order() default Integer.MAX_VALUE;

    /**
     * 是否忽略该字段
     */
    boolean ignored() default false;

    /**
     * 日期字符串转成Date类型的格式化串 仅对Date类型的属性有效
     */
    String format() default "yyyyMMdd";

}