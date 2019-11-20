package com.github.bluecatlee.bcm.utils;

public class MyStringUtils {

    /**
     * 获取某个字符第n次出现的位置
     * @param content 原字符串
     * @param c 字符
     * @param n 出现次数
     * @return
     */
    public static int getPositionOfCharInAppearCount(String content, char c, int n) {
        int count = 0;
        int position = -1;
        char[] arr = content.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) {
                count++;
            }
            if (count == n) {
                position = i;
                break;
            }
        }
        return position;
    }

}
