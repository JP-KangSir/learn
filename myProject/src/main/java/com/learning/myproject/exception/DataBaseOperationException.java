package com.learning.myproject.exception;

import com.learning.myproject.constant.AppConstant;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: kjp
 * Date: 2018-12-31
 * Time: 13:28
 */
@Data
public class DataBaseOperationException extends RuntimeException {

    private static final long serialVersionUID = -2352884190977971960L;

    /**
     * 错误码
     */
    protected int code = AppConstant.DATABASE_OPER_ERROR;

    public DataBaseOperationException() {
        super();
    }

    public DataBaseOperationException(String message) {
        super(message);
    }

    public DataBaseOperationException(int code, String message) {
        super(message);
        this.code = code;
    }
    
}
