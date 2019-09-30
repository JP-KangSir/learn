package com.learning.myproject.exception;

/**
 * 自定义异常
 *
 * @author kjp
 * @date 2018-04-09
 */
public class MyException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private String msg;

  private Integer code = 101;

  public MyException(String msg) {
    super(msg);
    this.msg = msg;
  }

  public MyException(String msg, Throwable e) {
    super(msg, e);
    this.msg = msg;
  }

  public MyException(Integer code, String msg) {
    super(msg);
    this.msg = msg;
    this.code = code;
  }

  public MyException(String msg, Integer code, Throwable e) {
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
