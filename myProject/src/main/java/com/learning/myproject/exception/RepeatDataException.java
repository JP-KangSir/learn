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
public class RepeatDataException extends RuntimeException {

    private static final long serialVersionUID = 333164189508134716L;
    /**
     * 错误码
     */
    protected int code;

    public RepeatDataException() {
        super();
    }

    public RepeatDataException(String message) {
        super(message);
    }

    public RepeatDataException(int code, String message) {
        super(message);
        this.code = code;
    }
}
