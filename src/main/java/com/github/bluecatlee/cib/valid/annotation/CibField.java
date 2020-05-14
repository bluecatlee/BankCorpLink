package com.github.bluecatlee.cib.valid.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CibField {

    boolean required() default false;

}