package com.github.bluecatlee.ccb.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface CCBPayField {

    // 是否必传
    boolean required() default false;

    // 字段名重写 为空时表示字段名重写为大写值 否则取name属性指定的值
    String name() default "";

    // 是否参与mac计算
//    boolean mac() default false;

    // 参与mac计算的字段顺序 值越小 字段排序越靠前 -1表示不参与mac计算 注意排序值不要定义相同 否则顺序不确定
//    int macOrder() default -1;

    // 字符的最大长度 超过则抛出异常  -1表示不处理
    // 中文字符串的长度应该小于 maxLen / 3
//    int maxLen() default -1;

    // 字符串值的最大长度 超出长度会截取  -1表示不处理
    // 中文字符串的长度应该小于 maxLen / 3
//    @Deprecated // 业务层判断并截取长度
//    int cutLen() default -1;

    // 是否需要对中文特殊字符编码
    boolean needEscape() default false;

}
