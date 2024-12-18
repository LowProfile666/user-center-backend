package com.zsm.usercenter.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户的昵称
     */
    private String nickname;

    /**
     * 唯一的登录账号
     */
    private String userAccount;

    /**
     * 头像的 URL 地址
     */
    private String avatar;

    /**
     * 0 - 未知，1 - 男，2 - 女
     */
    private Integer gender;

    /**
     * 用户的登录密码
     */
    private String userPassword;

    /**
     * 用户的联系电话
     */
    private String phone;

    /**
     * 用户的电子邮件地址
     */
    private String email;

    /**
     * 0 - 正常，1 - 禁用
     */
    private Integer userStatus;

    /**
     * 数据插入时间
     */
    private Date createTime;

    /**
     * 数据更新时间
     */
    private Date updateTime;

    /**
     * 0 - 未删除，1 - 已删除（逻辑删除）
     */
    @TableLogic
    private Integer deleteFlag;

    /**
     * 0 - 普通用户，1 - 管理员
     */
    private Integer userRole;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}