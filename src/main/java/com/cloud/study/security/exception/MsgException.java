package com.cloud.study.security.exception;

import com.cloud.study.domain.Response;
import com.cloud.study.enums.ExceptionEnum;
import com.cloud.study.enums.ResponseState;

/**
 * @description: TODO
 * @author: dqq
 * @date: 2020/9/29 14:30
 */

public class MsgException extends RuntimeException{

    protected Response.Meta meta;

    private int code;  //异常状态码

    private String message;  //异常信息

    private Object data;//数据

    public MsgException(String message){
        this.meta = new Response.Meta(ResponseState.FAIL);
        meta.setCode(ExceptionEnum.RUNTIME_EXCEPTION.getCode());
        meta.setMessage(message);
        this.data =null;
    }

    public MsgException(String message,Object data){
        this.meta = new Response.Meta(ResponseState.FAIL);
        meta.setCode(ExceptionEnum.RUNTIME_EXCEPTION.getCode());
        meta.setMessage(message);
        this.data =data;
    }

    public MsgException(int code, String message) {
        this.meta = new Response.Meta(ResponseState.FAIL);
        meta.setCode(code);
        meta.setMessage(message);
        this.data = null;
    }
}
