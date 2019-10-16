package com.learning.myproject.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang.StringUtils类
 * <p>
 * Created by EricShen on 2017/6/2.
 *
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 */
public class MyStringUtils extends org.apache.commons.lang3.StringUtils {

    public static String lowerFirst(String str) {
        if (MyStringUtils.isBlank(str)) {
            return "";
        } else {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
    }

    public static String upperFirst(String str) {
        if (MyStringUtils.isBlank(str)) {
            return "";
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    /**
     * 判断一个字符串是否null或""
     *
     * @return boolean
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断一个字符串是否非null或""
     *
     * @return boolean
     */
    public static boolean isNotBlank(String str) {
        return !(isBlank(str));
    }

    /**
     * 判断一个字符串是否null或""或"null"
     *
     * @return boolean
     */
    public static boolean isBlanks(String str) {
        return str == null || str.trim().length() == 0 || "null".equals(str.trim());
    }

    /**
     * 判断一个字符串是否非null或""或"null"
     *
     * @return boolean
     */
    public static boolean isNotBlanks(String str) {
        return !(isBlanks(str));
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        return m.replaceAll("");
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val) {
        return toLong(val).intValue();
    }

    /**
     * 逗号间隔id分字符串并返回字符串list
     *
     * @param ids id串
     * @return
     */
    public static List<String> splitByCommaAndMoveDuplicate(String ids) {
        if (isBlank(ids)) {
            return null;
        }
        String[] split = MyStringUtils.split(ids.trim(), ",");
        if (split.length < 1) {
            return null;
        }
        List<String> idList = Arrays.asList(split);
        idList = new ArrayList<>(new HashSet<>(idList));
        return idList;
    }

    /**
     * 判断多个字符串是否相同
     * @param args
     * @return
     * @author tongwei 
     */
    public static boolean equals(String... args) {
        if (args.length <= 1) {
            return true;
        }
        long size = Arrays.stream(args).distinct().count();
        if (size <= args.length) {
            return false;
        }
        return true;
    }
}
