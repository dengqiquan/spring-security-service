package com.cloud.study.security.enums;

/**
 * 定义异常枚举
 */
public enum ErrorCodeEnum {

    ERROR_PASSWORD(10001, "密码错误!"),
    ERROR_ACCOUNT(10002, "账号错误!"),
    NOT_EXIST_ACCOUNT(10003,"用户不存在!"),
    ERROR_USERNAME(10004,"用户错误!"),
    BAD_REQUEST_HEADER(10006,"请求头缺失Authorization!"),
    EXCHANGE_JSON_EXCEPTION(10007,"json转换异常!");

    private int code = 0;
    private String msg = "";

    ErrorCodeEnum(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }
}
