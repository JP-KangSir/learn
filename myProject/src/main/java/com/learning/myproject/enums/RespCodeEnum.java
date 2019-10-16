package com.learning.myproject.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * 返回状态码枚举值
 * 
 * @author: kjp
 * Date: 2018/9/13
 * Time: 上午10:12
 */
public enum RespCodeEnum {
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),
    
    PARAMETER_EROOR(5100, "参数校验失败"),

    PWD_EROOR(5101, "密码错误"),

    OPERATE_FAILURE(5000, "操作失败"),

    DATA_IS_EXIST(5409, "资源已存在"),

    DATA_NO_EXIST(5404, "资源不存在");
    

    private int code;

    private String message;

    public String message() {
        return message;
    }

    public int code() {
        return code;
    }

    RespCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * get enum
     * @param code
     * @return
     */
    public static RespCodeEnum getEnum(int code) {
        for (RespCodeEnum en : RespCodeEnum.values()) {
            if (en.code() == code) {
                return en;
            }
        }
        return null;
    }

    public static RespCodeEnum getEnum(String message) {
        for (RespCodeEnum en : RespCodeEnum.values()) {
            if (en.message.equals(message)) {
                return en;
            }
        }
        return null;
    }

}
