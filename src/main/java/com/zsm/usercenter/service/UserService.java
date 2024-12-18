package com.zsm.usercenter.service;

import com.zsm.usercenter.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author 20620
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-12-17 21:20:19
*/
public interface UserService extends IService<User> {
    /**
     * 注册
     * @param userAccount 账户
     * @param userPassword 密码
     * @param confirmPassword 确认密码
     * @return 注册的用户id
     */
    Long register(String userAccount, String userPassword, String confirmPassword);

    /**
     * 登录
     * @param userAccount 账户
     * @param userPassword 密码
     * @param request 请求对象
     * @return 登录的用户信息
     */
    User login(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 脱敏
     * @param user 原始用户信息
     * @return 脱敏后的安全用户信息
     */
    User getSafetyUser(User user);

    /**
     * 注销
     * @param request 请求对象
     * @return 成功1
     */
    Integer logout(HttpServletRequest request);
}
