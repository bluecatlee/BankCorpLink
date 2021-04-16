package com.github.bluecatlee.ccb.utils;

import java.math.BigDecimal;

public class MathUtil {

    public static String yuanToCent(String yuan) {
        BigDecimal multiply = new BigDecimal(yuan).multiply(new BigDecimal("100"));
        BigDecimal decimal = multiply.setScale(0);
        return decimal.toString();
    }

    public static Double centToYuan(Integer total_fee) {
        BigDecimal divide = new BigDecimal(total_fee).divide(new BigDecimal("100"));
        BigDecimal bigDecimal = divide.setScale(2);
        return bigDecimal.doubleValue();
    }

    public static BigDecimal centToYuan2(Integer total_fee) {
        BigDecimal divide = new BigDecimal(total_fee).divide(new BigDecimal("100"));
        BigDecimal bigDecimal = divide.setScale(2);
        return bigDecimal;
    }
}
