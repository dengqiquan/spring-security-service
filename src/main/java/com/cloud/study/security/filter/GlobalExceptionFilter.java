package com.cloud.study.security.filter;

import com.cloud.study.domain.Response;
import com.cloud.study.security.enums.ErrorCodeEnum;
import com.cloud.study.security.exception.SecurityGlobalException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 全局异常捕获
 * @author: dqq
 * @date: 2020/9/29 10:26
 */
@ControllerAdvice
public class GlobalExceptionFilter {

    /**
     * 异常捕获
     * @param exception
     * @return
     */
    @ExceptionHandler(value = SecurityGlobalException.class)
    @ResponseBody
    public Response handleServiceException(SecurityGlobalException exception) {
        ErrorCodeEnum errorCode = exception.getErrorCode();
        Response response = new Response();
        if (errorCode != null){
            response.failure(errorCode.getMsg());
            response.getMeta().setCode(errorCode.getCode());
        }else {
            response.failure(exception.getMsg());
            response.getMeta().setCode(exception.getCode());
        }
        response.setData(null);
        return response;
    }
}
