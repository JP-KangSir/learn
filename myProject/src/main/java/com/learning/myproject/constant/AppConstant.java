package com.learning.myproject.constant;

/**
 * 错误返回常量类
 *
 * @author ling
 * @date 2018/6/26
 */
public class AppConstant {


    /**
     * 通用返回成功
     */
    public static final Integer SUCCESS = 100;

    /**
     * 内部业务异常
     */
    public static final Integer BUSSINESS_ERROR = 500;

    public static final String VERSION = "1.0.0";

    public static final String SUCCESS_MSG = "请求成功";

    public static final Integer PARAM_LACK = 201;

    public static final String PARAM_LACK_MSG = "参数不足";

    public static final Integer ERROR_VALIDATE_FAILURE = 202;

    public static final String ERROR_VALIDATE_FAILURE_MSG = "token校验失败";

    public static final Integer ERROR_WRONG_ACCOUNT = 203;

    public static final String ERROR_WRONG_ACCOUNT_MSG = "账号密码错误";

    public static final Integer ERROR_NOT_EXIST_USER=300;

    public static final String  ERROR_NOT_EXIST_USER_MSG="用户不存在";

    public static final Integer ERROR_NOT_LOGIN = 204;

    public static final String ERROR_NOT_LOGIN_MSG = "尚未登录，请登录!";

    public static final Integer ERROR_COMMON = 205;

    public static final String ERROR_COMMON_MSG = "错误";

    public static final Integer ERROR_WRONG_ACCOUNT_FORBIDDEN = 207;

    public static final String ERROR_WRONG_ACCOUNT_FORBIDDEN_MSG = "该账号已被禁用,请联系管理员";

    public static final Integer FORBIDDEN = 401;

    public static final String FORBIDDEN_MSG = "您的账号被禁止登录,请联系管理员";

    public static final Integer ERROR_PAGE_NOT_FOUND = 404;

    public static final String ERROR_PAGE_NOT_FOUND_MSG = "接口不存在";

    public static final Integer ERROR_EXCEPTION = 500;

    public static final String ERROR_EXCEPTION_MSG = "未知异常，请联系管理员";

    /**
     * 登陆相关
     */
    public static final Integer ERROR_PERMISSION_DENIED = 1000;

    public static final String ERROR_PERMISSION_DENIED_MSG = "没有权限，请联系管理员授权";

    public static final Integer ERROR_SYS_USER_AUTHENTICATION_OVERDUE = 1001;

    public static final String ERROR_SYS_USER_AUTHENTICATION_OVERDUE_MSG = "登陆认证过期";

    public static final Integer ERROR_SYS_USER_NICKNAME = 1002;

    public static final String ERROR_SYS_USER_NICKNAME_MSG = "账号名称重复，请重新填写";

    /**
     * 角色相关
     */
    public static final Integer ERROR_SYS_ROLE_DATA_EMPTY = 1601;

    public static final String ERROR_SYS_ROLE_DATA_EMPTY_MSG = "该查询条件下无数据";

    public static final Integer ERROR_SYS_ROLE_INSERT_FAIL = 1602;

    public static final String ERROR_SYS_ROLE_INSERT_FAIL_MSG = "角色名重复";

    public static final Integer ERROR_SYS_ROLE_UPDATE_FAIL = 1603;

    public static final String ERROR_SYS_ROLE_UPDATE_FAIL_MSG = "修改失败";

    public static final Integer ERROR_SYS_ROLE_DELETE_FAIL = 1604;

    public static final String ERROR_SYS_ROLE_DELETE_FAIL_MSG = "删除失败,该角色下仍有用户";

    public static final Integer ERROR_SYS_ROLE_LEAST_ONE_PERMISSION_REQUIRED = 1605;

    public static final String ERROR_SYS_ROLE_LEAST_ONE_PERMISSION_REQUIRED_MSG = "至少选择一项权限";

    public static final Integer DATABASE_OPER_ERROR = 5500;

    /**
     * 请求参数相关
     */
    public static final Integer ERROR_REQUIRED_PARAM_NONSTANDARD = 5100;

    public static final String ERROR_REQUIRED_PARAM_NONSTANDARD_MSG = "请求参数不符合规范";

    public static final Integer PARAM_NULL_CODE = 5101;

    public static final String PARAM_NULL_CODE_MSG = "参数不能为空";

    /**
     * 日期相关
     */
    public static final Integer ERROR_DATE_FORMAT = 5200;

    public static final String ERROR_DATE_FORMAT_MSG = "日期参数格式不正确";

    public static final Integer DATA_NOT_EXIST = 5400;

    public static final String DATA_NOT_EXIST_MSG = "数据不存在";

    public static final Integer FIELD_NOT_MODIFY = 5401;

    public static final String FIELD_NOT_MODIFY_MSG = "字段不允许修改";

    /**
     * 数据库相关
     */
    public static final Integer ERROR_DATABASE_DUPLICATE_KEY = 6000;

    public static final String ERROR_DATABASE_DUPLICATE_KEY_MSG = "数据库中已存在该记录";


    public static final Integer ERROR_UPLOAD_SIZE_EXCEEDED = 7000;

    public static final String ERROR_UPLOAD_SIZE_EXCEEDED_MSG = "上传文件过大";


}
