package com.learning.myproject.util;

import com.learning.myproject.constant.MsgConstant;
import com.learning.myproject.enums.RespCodeEnum;
import com.learning.myproject.result.Result;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

/**
 * 通用工具类
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 * @date 2018-04-09
 */
public class CommonUtils {

    /**
     * 对象是否为空
     */
    public static boolean isNullOrEmpty(Object obj) {
        return ObjectUtils.isEmpty(obj);
    }

    /**
     * 判断整数是否大于零
     */
    public static boolean isIntThanZero(int number) {
        return number > 0;
    }

    /**
     * 新增，修改提示
     */
    public static Result msg(Integer count) {
        if (isIntThanZero(count)) {
            return Result.ok(MsgConstant.MSG_OPERATION_SUCCESS);
        }
        return Result.error(MsgConstant.MSG_OPERATION_FAILED);
    }

    /**
     * 新增，修改提示
     */
    public static Result msg(Boolean flag) {
        if (flag) {
            return Result.ok(MsgConstant.MSG_OPERATION_SUCCESS);
        }
        return Result.error(RespCodeEnum.OPERATE_FAILURE.code(), MsgConstant.MSG_OPERATION_FAILED);
    }

    /**
     * 查询详情提示
     */
    public static Result msg(Object data) {
        if (isNullOrEmpty(data)) {
            return Result.ok(MsgConstant.MSG_INIT_FORM);
        }
        return Result.ok(data);
    }

    /**
     * 返回数据
     */
    public static Result msgNotCheckNull(Object data) {
        return Result.ok(data);
    }

    /**
     * 删除提示
     */
    public static Result msg(Object[] total, int count) {
        if (total.length == count) {
            return Result.ok(MsgConstant.MSG_OPERATION_SUCCESS);
        } else {
            if (isIntThanZero(count)) {
                return Result.error(MsgConstant.batchOperation(total.length, count));
            } else {
                return Result.error(MsgConstant.MSG_OPERATION_FAILED);
            }
        }
    }

    /**
     * 批量新增提示
     */
    public static Result msg(Collection collection, int count) {
        if (collection.size() == count) {
            return Result.ok(MsgConstant.MSG_OPERATION_SUCCESS);
        } else {
            if (isIntThanZero(count)) {
                return Result.error(MsgConstant.batchOperation(collection.size(), count));
            } else {
                return Result.error(MsgConstant.MSG_OPERATION_FAILED);
            }
        }
    }

    /**
     * 字节转换
     *
     * @param size
     * @return
     */
    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }

    /**
     * 添加<![CDATA[文字]]>保护
     *
     * @param str
     * @return
     */
    public static String htmlSpecialchars(String str) {
        str = str.replaceAll("'", "").replaceAll("\r", "").replaceAll("\n", "");
        str = "<![CDATA[" + str + "]]>";
        return str;
    }

    /**
     * 去除<![CDATA[文字]]>保护
     *
     * @param str
     * @return
     */
    public static String removeSpecialchars(String str) {
        str = str.replace("<![CDATA[", "").replace("]]>", "");
        return str;
    }

    /**
     * 1到无限的通用码生成
     *
     * @param no
     * @return
     */

    public static String generateCommonNo(String no) {
        if (org.apache.commons.lang.StringUtils.isBlank(no)) {
            return "1";
        } else {
            Integer integer = Integer.valueOf(no) + 1;
            return String.valueOf(integer);
        }
    }

    /**
     * 去掉时间中间的t
     *
     * @param localDateTime
     * @return
     */
    public static String formateTime(LocalDateTime localDateTime) {
        if (null == localDateTime) {
            return null;
        }
        String time = localDateTime.toString().replaceAll("T", " ");
        return time;
    }


    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
