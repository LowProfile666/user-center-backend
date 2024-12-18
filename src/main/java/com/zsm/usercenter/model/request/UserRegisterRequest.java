package com.zsm.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Author : ZSM
 * Time :  2024/12/18
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userAccount;
    private String userPassword;
    private String confirmPassword;
}
