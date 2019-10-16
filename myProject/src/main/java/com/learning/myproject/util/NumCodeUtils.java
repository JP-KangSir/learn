package com.learning.myproject.util;

import java.time.LocalDate;

/**
 * @program: xdx
 * @description: 编码工具
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 * @create: 2019-01-07 10:49
 */
public class NumCodeUtils {

    private final static Integer INDEX=1;

    private final static String MSG="存在异常编号，请联系管理员";


    /**
     * 获得工装工程单编号
     *
     * @param lastNum
     * @param companyCode
     * @return
     */
    public static String getFrockOrderNum(String lastNum, String companyCode) {
        StringBuilder sb = new StringBuilder();
        String year = getYear();
        String month = getMonth();
        if (lastNum == null) {
            sb.append(1).append(companyCode).append(year).append(month).append("001");
            return sb.toString();
        } else {
            String replace = lastNum.replace(1 + companyCode, "");
            if (replace.startsWith(year + month)) {
                sb.append(1).append(companyCode).append(Integer.parseInt(replace) + 1);
            } else {
                sb.append(1).append(companyCode).append(year).append(month).append("001");
            }
            return sb.toString();
        }
    }


    public static synchronized String getApprovalCode() {
        return "SP" + System.currentTimeMillis();
    }

    /**
     * 获得年的后2位
     *
     * @return
     */
    private static String getYear() {
        int year = LocalDate.now().getYear();
        return String.valueOf(year).substring(2);
    }

    /**
     * 获得月份(01)
     *
     * @return
     */
    private static String getMonth() {
        int month = LocalDate.now().getMonth().getValue();
        String monthStr = String.valueOf(month);
        if (month < 10) {
            monthStr = "0" + monthStr;
        }
        return monthStr;
    }


    /**
     * 根据编号及需要生产的编号位数，在code前面补0
     *
     * @param code
     * @param length
     * @return
     */
    public static String complementaryDigits(String code, Integer length) {
        StringBuilder stringBuilder = new StringBuilder(code);
        while (stringBuilder.length() < length) {
            stringBuilder = new StringBuilder("0").append(stringBuilder);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取年2位+月2位
     *
     * @return
     */
    public static String getYearMonthNo() {
        return getYear() + getMonth();
    }

    /**
     * 生成serialNumber位数的最大值
     *
     * @param serialNumber 序号位数
     * @return
     */
    private static Integer getSerialNoMax(Integer serialNumber) {
        Integer serialNoMax = 0;
        for (Integer number = 0; number < serialNumber; number++) {
            Integer digits = (int) Math.pow(10, number.doubleValue());
            serialNoMax += 9 * digits;
        }
        return serialNoMax;
    }

    /**
     * 生成serialNumber位数的起始值
     *
     * @param serialNumber 序号位数
     * @return
     */
    private static String getSerialNoMin(Integer serialNumber) {
        StringBuilder stringBuilder = new StringBuilder("1");
        for (Integer number = 1; number < serialNumber; number++) {
            stringBuilder = new StringBuilder("0").append(stringBuilder);
        }
        return stringBuilder.toString();
    }

    /**
     * 生成“固定字符串+年2位+月2位+序号”规则的编号
     *
     * @param noTime       固定字符串+年2位+月2位
     * @param oldNoMax     当月已存在的序号最大值
     * @param serialNumber 序号位数
     * @return
     */
    public static String getSerialNo(String noTime, String oldNoMax, Integer serialNumber) {
        String no;
        if (null == oldNoMax || "".equals(oldNoMax)) {
            no = noTime + getSerialNoMin(serialNumber);
        } else {
            String a = oldNoMax.substring(noTime.length(), oldNoMax.length());
            Integer serialNo = Integer.parseInt(oldNoMax.substring(noTime.length(), oldNoMax.length())) + 1;
            if (serialNo > getSerialNoMax(serialNumber)) {
                return null;
            }
            no = noTime + NumCodeUtils.complementaryDigits(serialNo.toString(), 7);
        }
        return no;
    }

    public static String getSerialCodeDigit(String oldSerial ,Integer digit){
         if (oldSerial==null){
            String code=getSerialNoMin(digit);
            return complementaryDigits(code,digit);
        }else{
            String code = Integer.toString(Integer.valueOf(oldSerial)+1);
            return complementaryDigits(code,digit);
        }
    }

    public static String getPrefixSerialCode(String prefix, String oldSerial){
        if (oldSerial==null){
            return prefix+INDEX;
        }else{
            int length = prefix.length();
            String serial = oldSerial.substring(length);
            Integer code=Integer.valueOf(serial);
            return prefix+(code+1);
        }
    }

    public static String getPrefixSerialCodeDigit(String oldSerial ,String prefix,Integer digit){
        int prefixLength = prefix.length();
        if (oldSerial!=null){
            oldSerial=oldSerial.substring(prefixLength);
        }
        return prefix+getSerialCodeDigit(oldSerial,digit);
    }

}
