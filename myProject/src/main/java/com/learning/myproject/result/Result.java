package com.learning.myproject.result;

import com.learning.myproject.constant.AppConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 页面响应entity
 *
 * @author EricShen
 * @date 2018-04-09
 */

@Data
@ApiModel("Response通用Bean")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("版本")
    private String version ;

    @ApiModelProperty("结果状态码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String msg;

    @ApiModelProperty("数据")
    private T data;

    /**
     * 成功提示
     *
     * @return
     */
    public static Result ok() {
        Result result = new Result();
        result.setCode(AppConstant.SUCCESS);
        result.setMsg("SUCCESS");
        result.setCode(AppConstant.SUCCESS);
        return result;
    }

    public Result() {

        this.version = AppConstant.VERSION;
    }

    /**
     * 成功提示
     *
     * @param msg 提示信息
     * @return
     */
    public static <T> Result<T> ok(String msg) {
        Result<T> r = new Result<>();
        r.setCode(AppConstant.SUCCESS);
        r.setMsg(msg);
        return r;
    }

    public static <T> Result<T> okInString(T msg) {
        Result<T> r = new Result<>();
        r.setCode(AppConstant.SUCCESS);
        r.setData(msg);
        return r;
    }

    public static Result loginOk(String token) {
        Result<String> r = new Result<>();
        r.setCode(AppConstant.SUCCESS);
        r.setData(token);
        return r;
    }

    /**
     * 成功提示
     *
     * @param obj data
     * @return
     */
    public static <T> Result<T> ok(T obj) {
        Result<T> r = new Result<>();
        r.setCode(AppConstant.SUCCESS);
        r.setData(obj);
        return r;
    }


    /**
     * 错误提示
     *
     * @return
     */
    public static <T> Result<T> error() {
        return error(AppConstant.BUSSINESS_ERROR, "未知异常，请联系管理员");
    }

    /**
     * 错误提示
     *
     * @param msg 提示信息
     * @return
     */
    public static <T> Result<T> error(String msg) {
        return error(AppConstant.BUSSINESS_ERROR, msg);
    }

    /**
     * 错误提示
     *
     * @param code 返回码
     * @param msg  提示信息
     * @return
     */
    public static <T> Result<T> error(int code, String msg) {
        Result<T> r = new Result<>();
        r.setMsg(msg);
        r.setCode(code);
        r.setData(null);
        return r;
    }

    public static <T> Result<T> error(int code, T data, String msg) {
        Result<T> r = new Result<>();
        r.setMsg(msg);
        r.setData(data);
        r.setCode(code);
        return r;
    }

}