package com.github.bluecatlee.ccb.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static final String Get_Price_bits = "0.00";
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
    public static final Pattern INTEGER_PATTERN = Pattern.compile("^-?[\\d]+$");
    public static final String INVALID_CDATA_TEXT = "]]>";
    public static final String CHINESE_REGEX = "[\\u4e00-\\u9fa5]";
    private static final int MAX_UNICODE_DATA = 125;
    public static final Pattern BLANKSPACE_PATTERN = Pattern.compile("\\s+");
    public static final Pattern PASSWORD_PATTERN = Pattern.compile("\\W+");
    public static final Pattern HTML_PATTERN = Pattern.compile("<[^>]+>");
    public static final Pattern DATE_PATTERN = Pattern.compile("^(\\d{2,4})\\-(\\d{1,2})\\-(\\d{1,2})$");
    public static final Pattern TIME_PATTERN = Pattern.compile("^(\\d{2,4})\\-(\\d{1,2})\\-(\\d{1,2})\\s(\\d{1,2}):(\\d{1,2}):(\\d{1,2})$");
    public static final Pattern TIME_OTHER_PATTERN = Pattern.compile("^(\\d{2,4})\\/(\\d{1,2})\\/(\\d{1,2})\\s(\\d{1,2}):(\\d{1,2}):(\\d{1,2})$");
    public static final Pattern ACCOUNT_PATTERN = Pattern.compile("^[a-zA-Z]\\w+$");
    public static final String Empty = "";

    public StringUtil() {
    }

    public static boolean isEmail(String email) {
        boolean ret = false;
        String emailPat = "^([a-z0-9A-Z]+[-|\\_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

        try {
            if(email.matches(emailPat)) {
                ret = true;
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return ret;
    }

    public static boolean isSafeXmlString(String xml) {
        if(isNullOrBlankTrim(xml)) {
            return true;
        } else {
            for(int i = 0; i < xml.length(); ++i) {
                Character c = Character.valueOf(xml.charAt(i));
                if(c.charValue() < 32 && c.charValue() != 9 & c.charValue() != 10 & c.charValue() != 13) {
                    return false;
                }
            }

            return xml.indexOf("]]>") == -1;
        }
    }

    public static String getSafeXmlString(String xml) {
        return getSafeXmlString(xml, true);
    }

    public static String getSafeXmlString(String xml, boolean isCdata) {
        if(xml == null) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < xml.length(); ++i) {
                Character c = Character.valueOf(xml.charAt(i));
                if(c.charValue() >= 32 || !(c.charValue() != 9 & c.charValue() != 10 & c.charValue() != 13)) {
                    sb.append(c);
                }
            }

            if(!isCdata) {
                return sb.toString();
            } else {
                String returnString;
                for(returnString = sb.toString(); returnString.indexOf("]]>") != -1; returnString = returnString.replace("]]>", "]]")) {
                    ;
                }

                return returnString;
            }
        }
    }

    public static String replaceNewline(String str) {
        return isNullOrBlank(str)?"":str.replaceAll("\\n", "").replaceAll("\\r\\n", "");
    }

    public static boolean isNullOrBlank(String param) {
        return param == null || "".equals(param);
    }

    public static boolean isNullOrBlankTrim(Object param) {
        return param == null || "".equals(param.toString().trim());
    }

    public static boolean isNullOrBlankTrim(String param) {
        return param == null || "".equals(param.toString().trim());
    }

    public static boolean isBlankSpace(String param) {
        if(param == null) {
            return true;
        } else {
            Matcher m = BLANKSPACE_PATTERN.matcher(param);
            return m.find();
        }
    }

    public static String nullToBlankStr(Object param) {
        return nullToDefaultValue(param, "");
    }

    public static String nullToDefaultValue(Object param, String defaultValue) {
        return param == null?defaultValue:param.toString();
    }

    public static String nullOrBlankToDefault(Object param, String defaultValue) {
        return param == null?defaultValue:(param instanceof String && isNullOrBlankTrim((String)param)?defaultValue:param.toString());
    }

    public static boolean isNumber(String param) {
        if(param == null) {
            return false;
        } else {
            Matcher m = NUMBER_PATTERN.matcher(param);
            return m.find();
        }
    }

    public static boolean isNullOrNumber(String param) {
        if(param == null) {
            return true;
        } else {
            Matcher m = NUMBER_PATTERN.matcher(param);
            return m.find();
        }
    }

    public static boolean isOtherTime(String param) {
        if(param != null) {
            Matcher m = TIME_OTHER_PATTERN.matcher(param);
            if(m.find()) {
                return true;
            }
        }

        return false;
    }

    public static boolean isDate(String param) {
        if(param != null) {
            Matcher m = DATE_PATTERN.matcher(param);
            if(m.find()) {
                return true;
            }
        }

        return false;
    }

    public static boolean isTime(String param) {
        if(param != null) {
            Matcher m = TIME_PATTERN.matcher(param);
            if(m.find()) {
                return true;
            }
        }

        return false;
    }

    public static boolean isInteger(String param) {
        if(param == null) {
            return false;
        } else {
            Matcher m = INTEGER_PATTERN.matcher(param);
            return m.find();
        }
    }

    public static boolean isNullOrInteger(String param) {
        if(param == null) {
            return true;
        } else {
            Matcher m = INTEGER_PATTERN.matcher(param);
            return m.find();
        }
    }

    public static boolean isAllNullOrBlank(String... objs) {
        if(objs.length == 1) {
            return isNullOrBlank(objs[0]);
        } else {
            String[] var1 = objs;
            int var2 = objs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String s = var1[var3];
                if(isNullOrBlank(s)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isAllNotNullOrBlank(String... objs) {
        if(objs.length == 1) {
            return !isNullOrBlank(objs[0]);
        } else {
            String[] var1 = objs;
            int var2 = objs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String s = var1[var3];
                if(isNullOrBlank(s)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isAllNotNullOrBlank(Object... objs) {
        if(objs.length == 1) {
            return !isNullOrBlankTrim(objs[0]);
        } else {
            Object[] var1 = objs;
            int var2 = objs.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Object s = var1[var3];
                if(isNullOrBlankTrim(s)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static int getOracleLength(String str) {
        int ret = -1;
        if(str == null) {
            return ret;
        } else {
            String temp = str.replaceAll("[\\u4e00-\\u9fa5]", "***");
            return temp.length();
        }
    }

    public static boolean isOverOracleLength(String str, int maxLength) {
        int length = getOracleLength(str);
        return length > maxLength;
    }

    public static String[] getSplitArray(String strs, String regExp, int limit) {
        return strs.split(regExp, limit);
    }

    public static Long[] getSplitLongArray(String strs, String regExp) {
        String[] s = getSplitArray(strs, regExp, 0);
        return strArrToLongArr(s);
    }

    public static Long[] strArrToLongArr(String[] strArray) {
        Long[] rl = new Long[strArray.length];

        for(int i = 0; i < strArray.length; ++i) {
            rl[i] = Long.valueOf(Long.parseLong(strArray[i]));
        }

        return rl;
    }

    public static boolean isInBetweenOracleLength(String str, int minLength, int maxLength) {
        int length = getOracleLength(str);
        return length >= minLength && length <= maxLength;
    }

    public static String filterHTML(String htmlString) {
        if(htmlString == null) {
            return null;
        } else {
            Matcher m_html = HTML_PATTERN.matcher(htmlString);
            return m_html.replaceAll("?");
        }
    }

    public static boolean isNull(Object o) {
        if(o == null) {
            return true;
        } else if(o instanceof Collection) {
            Collection c = (Collection)o;
            return c.isEmpty();
        } else {
            return false;
        }
    }

    public static String messageFormatParser(String msg, Object param) {
        if(!isNullOrBlankTrim(msg) && !isNull(param)) {
            MessageFormat mf = new MessageFormat(msg);
            if(!(param instanceof String) && !(param instanceof Integer) && !(param instanceof Float) && !(param instanceof Double)) {
                if(param instanceof Object[]) {
                    return mf.format((Object[])((Object[])param));
                } else if(param instanceof List) {
                    List p = (List)param;
                    return mf.format(p.toArray());
                } else {
                    return mf.format(new Object[]{param});
                }
            } else {
                return mf.format(new Object[]{param});
            }
        } else {
            return "";
        }
    }

    public static String getOneStringByRegex(String con, String reg) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(con);
        return m.matches()?m.group(1):"";
    }

    public static List<String> getStringsByRegex(String regex, int[] index, String txt) {
        List<String> res = new ArrayList();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(txt);
        if(m.find()) {
            int[] var6 = index;
            int var7 = index.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                int i = var6[var8];
                res.add(m.group(i));
            }
        }

        return res;
    }

    public static String map2String(Map map) {
        if(map != null && !map.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Object[] keys = map.keySet().toArray();
            Object[] var3 = keys;
            int var4 = keys.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Object key = var3[var5];
                sb.append(key).append("->").append(map.get(key)).append(" ");
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    public static String list2StringWithComma(List list) {
        if(list != null && !list.isEmpty()) {
            StringBuffer buf = new StringBuffer();

            for(int i = 0; i < list.size(); ++i) {
                Object o = list.get(i);
                if(!(o instanceof String) && !(o instanceof Number)) {
                    if(o instanceof List) {
                        buf.append(list2StringWithComma((List)o));
                    }
                } else {
                    buf.append(o.toString());
                }

                if(i < list.size() - 1) {
                    buf.append(",");
                }
            }

            return buf.toString();
        } else {
            return "";
        }
    }

    public static Float getPriceWith2bit(Float price) {
        DecimalFormat df = new DecimalFormat("0.00");
        String res = df.format(price);
        return Float.valueOf(Float.parseFloat(res));
    }

    public static String formatParamMsg(String message, Object[] args) {
        for(int i = 0; i < args.length; ++i) {
            message = message.replace("{" + i + "}", args[i].toString());
        }

        return message;
    }

    public static String formatParamMsg(String message, Map params) {
        if(params == null) {
            return message;
        } else {
            Iterator keyIts = params.keySet().iterator();

            while(keyIts.hasNext()) {
                String key = (String)keyIts.next();
                Object val = params.get(key);
                if(val != null) {
                    message = message.replace("${" + key + "}", val.toString());
                }
            }

            return message;
        }
    }

    public static StringBuilder formatMsg(CharSequence msgWithFormat, boolean autoQuote, Object[] args) {
        int argsLen = args.length;
        boolean markFound = false;
        StringBuilder sb = new StringBuilder(msgWithFormat);
        if(argsLen > 0) {
            for(int i = 0; i < argsLen; ++i) {
                String flag = "%" + (i + 1);

                for(int idx = sb.indexOf(flag); idx >= 0; idx = sb.indexOf(flag)) {
                    markFound = true;
                    sb.replace(idx, idx + 2, toString(args[i], autoQuote));
                }
            }

            if(args[argsLen - 1] instanceof Throwable) {
                StringWriter sw = new StringWriter();
                ((Throwable)args[argsLen - 1]).printStackTrace(new PrintWriter(sw));
                sb.append("\n").append(sw.toString());
            } else if(argsLen == 1 && !markFound) {
                sb.append(args[argsLen - 1].toString());
            }
        }

        return sb;
    }

    public static String toString(Object obj, boolean autoQuote) {
        StringBuilder sb = new StringBuilder();
        if(obj == null) {
            sb.append("NULL");
        } else if(obj instanceof Object[]) {
            for(int i = 0; i < ((Object[])((Object[])((Object[])obj))).length; ++i) {
                sb.append(((Object[])((Object[])((Object[])obj)))[i]).append(", ");
            }

            if(sb.length() > 0) {
                sb.delete(sb.length() - 2, sb.length());
            }
        } else {
            sb.append(obj.toString());
        }

        if(autoQuote && sb.length() > 0 && (sb.charAt(0) != 91 || sb.charAt(sb.length() - 1) != 93) && (sb.charAt(0) != 123 || sb.charAt(sb.length() - 1) != 125)) {
            sb.insert(0, "[").append("]");
        }

        return sb.toString();
    }

    public static StringBuilder formatMsg(String msgWithFormat, Object[] args) {
        return formatMsg(new StringBuilder(msgWithFormat), true, args);
    }

    public static int charCount(String str, String oneChar) {
        int count = 0;
        str.indexOf(oneChar);

        while(str.indexOf(oneChar) != -1) {
            ++count;
            str = str.substring(str.indexOf(oneChar) + 1);
        }

        return count;
    }

    public static String getTableNameFromSql(String sql) {
        if(isNullOrBlankTrim(sql)) {
            return null;
        } else {
            sql = sql.toLowerCase().trim();
            String[] splitStrs = null;
            if(sql.startsWith("select")) {
                splitStrs = sql.split("from");
            } else if(sql.startsWith("update")) {
                splitStrs = sql.split("update");
            } else if(sql.startsWith("delete")) {
                splitStrs = sql.split("delete");
            } else {
                if(!sql.startsWith("insert")) {
                    return "";
                }

                splitStrs = sql.split("into");
            }

            String str = splitStrs[1];
            str = str.trim();
            str = str.split(" ")[0];
            return str;
        }
    }

    private static int charCount(String str, char c) {
        int count = 0;

        for(int i = 0; i < str.length(); ++i) {
            if(str.charAt(i) == c) {
                ++count;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(Integer.valueOf(1));
        String storageCondition = list2StringWithComma(list);
        String sql = "select s.* from SCM_ACC_INV_OWN_STK s where s.div_num_id = ? and s.item_num_id = ? and s.div_num_id = ? and s.loc_pty_num_id=? and s.storage_num_id in (" + storageCondition + ") order by s.storage_num_id desc";
        System.out.println(sql);
    }

    public static boolean isNotNullOrBlankTrim(Object param) {
        return param != null && !"".equals(param.toString().trim()) && !"null".equals(param.toString().trim());
    }
}
