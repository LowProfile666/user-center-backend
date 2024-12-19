package com.zsm.usercenter.model.response;

import lombok.*;

/**
 * Author : ZSM
 * Time :  2024/12/19
 */


@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum CodeEnum {
    SUCCESS(2000, "成功", ""),
    ERROR(5000, "错误", ""),
    PARAMS_ERROR(4000, "请求参数错误", ""),
    LOGIN_ERROR(4001, "登录失败", ""),
    REGISTER_ERROR(4002, "注册失败", ""),
    ROLE_ERROR(4003, "无权限", "");


    private int code;
    private String msg;
    private String detail;
}
