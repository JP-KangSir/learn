package com.learning.myproject.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * 数据不存在异常
 *
 * @author: kjp
 * Date: 2018/9/13
 * Time: 下午10:45
 */
@Setter
@Getter
public class DataNoExistException extends RuntimeException {

    private static final long serialVersionUID = 333164189508134716L;
    /**
     * 错误码
     */
    protected int code;

    public  static final String DISTRIBUTOR_PRICE_NOT_EXIST="该分销商无可用价目表，请先完善价目表信息";

    public  static final String DESIGNER_NOT_EXIST="该订单无可分配设计师，请先添加设计师";

    public DataNoExistException() {
        super();
    }

    public DataNoExistException(String message) {
        super(message);
    }

    public DataNoExistException(int code, String message) {
        super(message);
        this.code = code;
    }
}
