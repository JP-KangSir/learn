package com.learning.myproject.exception;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: kjp
 * Date: 2018-12-30
 * Time: 15:06
 */
@Data
public class ParamException extends RuntimeException{

    private static final long serialVersionUID = 9165316533491882253L;

    /**
     * 错误码
     */
    protected int code;

    public ParamException() {
        super();
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(int code, String message) {
        super(message);
        this.code = code;
    }
}
