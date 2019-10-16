package com.learning.myproject.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 */
public class StringUtils {

    private static final String SEPARATOR=",";

    public static String trim(String mata) {
        return mata.replace('　', ' ').trim();
    }

    public static String removeCommas(String mata) {
        mata = mata.trim();
        if (mata.length() < 1) {
            return mata;
        }
        String lastChar = mata.substring(mata.length() - 1);
        if (lastChar.equals(",")) {
            return mata.substring(0, mata.length() - 1);
        }
        return mata;
    }

    public static int StringToA(String content) {

        int max = content.length();
        for (int i = 0; i < max; i++) {
            char c = content.charAt(i);
            int b = (int) c;
            return b;
        }
        return 0;
    }


    public static String AToString(int i) {
        return Character.toString((char) i);
    }


    /**
     * 数组转字符串并去掉左右方括号
     *
     * @param collection
     * @return
     */
    public static String replaceBracket(Collection collection) {
        return collection.toString().replace("[", "").replace("]", "");
    }

    public static String getInCondition(List list) {
        StringBuilder result = new StringBuilder("");
        for (Object e : list) {
            if (e instanceof String) {
                result.append("'").append(e).append("'");
                if (list.indexOf(e) != list.size() - 1) {
                    result.append(",");
                }
            } else if (e instanceof Integer) {
                result.append(e);
                if (list.indexOf(e) != list.size() - 1) {
                    result.append(",");
                }
            }
        }
        return result.toString();
    }

    public static boolean isNotBlank(String str) {
        return org.apache.commons.lang3.StringUtils.isNotBlank(str);
    }

    public static String firstToLower(String str) {
        if (isNotBlank(str)) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        } else {
            return "";
        }
    }

    public static <T> String addElement(String target,T element){
        if (target==null || target.trim().equals("")){
            return element.toString();
        }else{
            return target+SEPARATOR+element;
        }
    }

    public static String addElement(String target,List<?> elements){
        if (target==null || target.trim().equals("")){
            return getInCondition(elements);
        }else{
            StringBuilder sb = new StringBuilder(target);
            for (Object object:elements){
                sb.append(SEPARATOR).append(object);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String target="2";
        String ele="2";
        List<Integer> e=new ArrayList<>();
        e.add(1);
        e.add(2);
        e.add(3);
        System.out.println(addElement(target,e));
        System.out.println(addElement(target,ele));

    }

}
