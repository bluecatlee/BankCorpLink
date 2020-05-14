package com.github.bluecatlee.cib.utils;

import org.springframework.lang.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author Bluecat Lee
 */
public class ReflectUtils {

    /**
     * 动态修改类字段的注解值
     *      注: 调用者保证同步
     * @param clazz 字段所属类
     * @param fieldName 字段名称
     * @param annotationClazz 字段的注解类型
     * @param annotationFieldName 待修改注解属性名
     * @param newValue 值
     * @throws Exception
     */
    @SuppressWarnings("all")
    public static void modAnnotationVal(Class clazz, String fieldName, @Nullable Class<? extends Annotation> annotationClazz,
                                         String annotationFieldName, String newValue) throws Exception {
        Field f = clazz.getDeclaredField(fieldName);
        Annotation annotation = null;
        if (annotationClazz == null) {
            Annotation[] declaredAnnotations = f.getDeclaredAnnotations();
            if (declaredAnnotations == null || declaredAnnotations.length == 0) {
                throw new Exception();
            }
            annotation = declaredAnnotations[0];
        } else {
            annotation = f.getAnnotation(annotationClazz);
        }
        if (annotation != null) {
            InvocationHandler ih = Proxy.getInvocationHandler(annotation);
            Field field = ih.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
            Map memberValues = (Map)field.get(ih);
            memberValues.put(annotationFieldName, newValue);
        }
    }

    @SuppressWarnings("all")
    public static void modAnnotationVal(Class clazz, Class fieldClazz, @Nullable Class<? extends Annotation> annotationClazz,
                                        String annotationFieldName, String newValue) throws Exception {
        Field f = null;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field: declaredFields) {
            if (field.getType().equals(fieldClazz)) {
                // 默认第一个类型匹配的字段
                f = field;
                break;
            }
        }
        if (f == null) {
            return;
        }
        Annotation annotation = null;
        if (annotationClazz == null) {
            Annotation[] declaredAnnotations = f.getDeclaredAnnotations();
            if (declaredAnnotations == null || declaredAnnotations.length == 0) {
                throw new Exception();
            }
            annotation = declaredAnnotations[0];
        } else {
            annotation = f.getAnnotation(annotationClazz);
        }
        if (annotation != null) {
            InvocationHandler ih = Proxy.getInvocationHandler(annotation);
            Field field = ih.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
            Map memberValues = (Map)field.get(ih);
            memberValues.put(annotationFieldName, newValue);
        }
    }

    /**
     * 获取某个类的字段注解的属性值
     * @param clazz
     * @param fieldName
     * @param annotationClazz
     * @param annotationFieldName
     * @return
     * @throws Exception
     */
    @SuppressWarnings("all")
    public static Object getAnnotationVal(Class clazz, String fieldName, @Nullable Class<? extends Annotation> annotationClazz,
                                        String annotationFieldName) throws Exception {
        Field f = clazz.getDeclaredField(fieldName);
        Annotation annotation = null;
        if (annotationClazz == null) {
            Annotation[] declaredAnnotations = f.getDeclaredAnnotations();
            if (declaredAnnotations == null || declaredAnnotations.length == 0) {
                throw new Exception();
            }
            annotation = declaredAnnotations[0];
        } else {
            annotation = f.getAnnotation(annotationClazz);
        }
        Object o = null;
        if (annotation != null) {
            InvocationHandler ih = Proxy.getInvocationHandler(annotation);
            Field field = ih.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
            Map memberValues = (Map)field.get(ih);
            o = memberValues.get(annotationFieldName);
        }
        return o;
    }

    @SuppressWarnings("all")
    public static Object getAnnotationVal(Class clazz, int fieldIndex, @Nullable Class<? extends Annotation> annotationClazz,
                                          String annotationFieldName) throws Exception {
        Field f = null;
        Field[] declaredFields = clazz.getDeclaredFields();
        if (declaredFields != null && declaredFields.length > 0) {
            f = declaredFields[fieldIndex];
        }
        if (f == null) {
            return null;
        }
        Annotation annotation = null;
        if (annotationClazz == null) {
            Annotation[] declaredAnnotations = f.getDeclaredAnnotations();
            if (declaredAnnotations == null || declaredAnnotations.length == 0) {
                throw new Exception();
            }
            annotation = declaredAnnotations[0];
        } else {
            annotation = f.getAnnotation(annotationClazz);
        }
        Object o = null;
        if (annotation != null) {
            InvocationHandler ih = Proxy.getInvocationHandler(annotation);
            Field field = ih.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
            Map memberValues = (Map)field.get(ih);
            o = memberValues.get(annotationFieldName);
        }
        return o;
    }

    @SuppressWarnings("all")
    public static Object getAnnotationVal(Class clazz, Class fieldClazz, @Nullable Class<? extends Annotation> annotationClazz,
                                          String annotationFieldName) throws Exception {
        Field f = null;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field: declaredFields) {
            if (field.getType().equals(fieldClazz)) {
                // 默认第一个类型匹配的字段
                f = field;
                break;
            }
        }
        if (f == null) {
            return null;
        }
        Annotation annotation = null;
        if (annotationClazz == null) {
            Annotation[] declaredAnnotations = f.getDeclaredAnnotations();
            if (declaredAnnotations == null || declaredAnnotations.length == 0) {
                throw new Exception();
            }
            annotation = declaredAnnotations[0];
        } else {
            annotation = f.getAnnotation(annotationClazz);
        }
        Object o = null;
        if (annotation != null) {
            InvocationHandler ih = Proxy.getInvocationHandler(annotation);
            Field field = ih.getClass().getDeclaredField("memberValues");
            field.setAccessible(true);
            Map memberValues = (Map)field.get(ih);
            o = memberValues.get(annotationFieldName);
        }
        return o;
    }

}
