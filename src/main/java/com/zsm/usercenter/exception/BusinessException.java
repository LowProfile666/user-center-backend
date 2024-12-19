package com.zsm.usercenter.exception;

import com.zsm.usercenter.model.response.CodeEnum;
import lombok.*;

/**
 * Author : ZSM
 * Time :  2024/12/19
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
    private int code;
    private String detail;

    public BusinessException() {}

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(int code, String msg, String detail) {
        super(msg);
        this.code = code;
        this.detail = detail;
    }

    public BusinessException(CodeEnum codeEnum) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
        this.detail = codeEnum.getDetail();
    }

    public BusinessException(CodeEnum codeEnum, String detail) {
        super(codeEnum.getMsg());
        this.code = codeEnum.getCode();
        this.detail = detail;
    }
}
