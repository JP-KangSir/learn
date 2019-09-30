package com.learning.myproject.exception;

public class CodeException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String msg;

    private Integer code = 101;

    public CodeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CodeException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CodeException(Integer code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CodeException(String msg, Integer code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
