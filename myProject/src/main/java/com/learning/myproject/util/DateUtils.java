package com.learning.myproject.util;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 * @Date: 2019/2/20 0020
 * @Time: 14:49
 */
public class DateUtils {

    /**
     * 返回当前时间的季度
     * */
    public static Integer getQuarter(){
        int monthValue = LocalDateTime.now().getMonthValue();
        return (monthValue/3)+1;
    }
    /**
     * 返回季度的月份字符串
     * */
    public static String getMonthStringByQuarter(Integer quarter){
        Integer maxMonth=quarter*3;
        return "("+(maxMonth-2)+","+(maxMonth-1)+","+maxMonth+")";
    }

}
