package com.cloud.study.security.exception;

import com.cloud.study.security.enums.ErrorCodeEnum;
import org.springframework.stereotype.Component;

/**
 * @description: 全局异常定义
 * @author: dqq
 * @date: 2020/9/28 12:31
 */
public class SecurityGlobalException extends RuntimeException{
    private ErrorCodeEnum errorCode;

    private int code;
    private String msg;



    public SecurityGlobalException(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }


    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
