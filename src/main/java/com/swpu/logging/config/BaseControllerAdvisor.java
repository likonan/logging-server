package com.swpu.logging.config;

import com.swpu.logging.common.exception.UnAuthException;
import com.swpu.logging.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
* 增强器处理全局异常
* 优先级
* 自定义异常>父类异常
* 如果能匹配到自定义异常则使用自定义异常处理
* 否则使用父类异常处理*/
@RestControllerAdvice
public class BaseControllerAdvisor {
    @ExceptionHandler(UnAuthException.class)
    public Result<?> handlerUnAuthException(UnAuthException e) {
        e.printStackTrace();
        return new Result<>().unAuth(e.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    public Result<?> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return new Result<>().error(e.getMessage());
    }

}
