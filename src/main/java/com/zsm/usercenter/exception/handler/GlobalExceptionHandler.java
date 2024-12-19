package com.zsm.usercenter.exception.handler;

import com.zsm.usercenter.exception.BusinessException;
import com.zsm.usercenter.model.response.CodeEnum;
import com.zsm.usercenter.model.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Author : ZSM
 * Time :  2024/12/19
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    public R handleBusinessException(BusinessException e) {
        log.error("BusinessException: " + e.getMessage(), e);
        return R.error(e.getCode(), e.getMessage(), e.getDetail());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public R handleRuntimeException(RuntimeException e) {
        log.error("RuntimeException: " + e.getMessage(), e);
        return R.error(CodeEnum.ERROR.getCode(), e.getMessage());
    }
}
