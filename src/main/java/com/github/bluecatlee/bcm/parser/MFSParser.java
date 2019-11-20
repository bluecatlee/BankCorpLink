package com.github.bluecatlee.bcm.parser;

import com.github.bluecatlee.bcm.annotation.Mfs;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MFSParser {

    /**
     * 多域串解析
     * @param content 多域串
     * @param separator 分隔字符
     * @param fieldNum 字段数
     * @param recordNum 记录数
     * @param clazz 解析成的bean类型
     * @param <T>
     * @return
     */
    public static <T> List<T> handleMfs(String content, char separator, Integer fieldNum, Integer recordNum, Class<T> clazz) {

        String[] arr = content.split("[" + separator + "]");  // 如果多域串最后一个域的值为空 可能会导致数据异常

        List<T> list = new ArrayList<>();

        int from = fieldNum;
        for (int i = 0; i < recordNum; i++) {
            String[] subArr = Arrays.copyOfRange(arr, from, from + fieldNum);
            T t = null;
            try {
                t = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field: fields) {
                    field.setAccessible(true);
                    Mfs mfsAnnotation = field.getAnnotation(Mfs.class);
                    if (mfsAnnotation == null || mfsAnnotation.ignored()) {
                        continue;
                    }
                    int order = mfsAnnotation.order();
                    if (order < 0 || order > subArr.length) {
                        continue;
                    }
                    String val = subArr[order];
                    Class<?> type = field.getType();
                    if (type.equals(String.class)) {
                        field.set(t, val);
                    } else if (type.equals(BigDecimal.class)) {
                        if (StringUtils.isBlank(val)) {
                            field.set(t, null);
                        } else {
                            field.set(t, new BigDecimal(val));
                        }
                    } else if (type.equals(Integer.class)) {
                        if (StringUtils.isBlank(val)) {
                            field.set(t, null);
                        } else {
                            field.set(t, new Integer(val));
                        }
                    } else if (type.equals(Long.class)) {
                        if (StringUtils.isBlank(val)) {
                            field.set(t, null);
                        } else {
                            field.set(t, new Long(val));
                        }
                    } else if (type.equals(int.class)) {
                        if (StringUtils.isBlank(val)) {
                            field.set(t, 0);
                        } else {
                            field.set(t, Integer.valueOf(val).intValue());
                        }
                    } else if (type.equals(long.class)) {
                        if (StringUtils.isBlank(val)) {
                            field.set(t, 0L);
                        } else {
                            field.set(t, Long.valueOf(val).longValue());
                        }
                    } else if (type.equals(Double.class)) {
                        if (StringUtils.isBlank(val)) {
                            field.set(t, null);
                        } else {
                            field.set(t, new Double(val));
                        }
                    } else if (type.equals(Date.class)) {
                        String format = mfsAnnotation.format();
                        if (StringUtils.isBlank(val) || StringUtils.isBlank(format)) {
                            field.set(t, null);
                        } else {
                            field.set(t, new SimpleDateFormat(format).parse(val));
                        }
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            list.add(t);
            from = from + fieldNum;
        }
        return list;
    }

}
