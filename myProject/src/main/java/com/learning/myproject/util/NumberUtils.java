package com.learning.myproject.util;

import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 *
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 * @Date: 2019/2/18 0018
 * @Time: 17:04
 */
public class NumberUtils {

    private static final Integer INTEGER_INDEX = 0;

    private static final Double DOUBLE_INDEX = 0.00;

    /**
     * 保留两位小数
     *
     * */
    public static Double keepTwoDecimal(Double number) {
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(number);
        return Double.valueOf(format);
    }

    /**
     * 自定义除法
     *
     * */
    public static Double division(Integer dividend, Integer divisor) {
        if (dividend == null || divisor == null || dividend.equals(INTEGER_INDEX) || divisor.equals(INTEGER_INDEX)) {
            return DOUBLE_INDEX;
        } else {
            return dividend.doubleValue() / divisor;
        }
    }


}
