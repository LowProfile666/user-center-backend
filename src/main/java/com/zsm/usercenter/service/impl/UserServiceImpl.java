package com.zsm.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsm.usercenter.constant.UserConstant;
import com.zsm.usercenter.exception.BusinessException;
import com.zsm.usercenter.model.User;
import com.zsm.usercenter.model.response.CodeEnum;
import com.zsm.usercenter.service.UserService;
import com.zsm.usercenter.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* @author 20620
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-12-17 21:20:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public Long register(String userAccount, String userPassword, String confirmPassword) {
        if (StringUtils.isAnyBlank(userAccount, userPassword, confirmPassword)) {
            throw new BusinessException(CodeEnum.REGISTER_ERROR, "注册参数为空！");
        }
        if (userAccount.length() < 4 || userPassword.length() < 8 || confirmPassword.length() < 8) {
            throw new BusinessException(CodeEnum.REGISTER_ERROR, "注册参数长度不合法！");
        }
        if (!userAccount.matches("^[a-zA-Z0-9]+$")) {
            throw new BusinessException(CodeEnum.REGISTER_ERROR, "账户只能由数字、英文组成！");
        }
        if (!userPassword.equals(confirmPassword)) {
            throw new BusinessException(CodeEnum.REGISTER_ERROR, "两次密码不一致！");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        if (this.getOne(queryWrapper) != null) {
            throw new BusinessException(CodeEnum.REGISTER_ERROR, "当前用户已存在！");
        }

        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encrypt(userPassword));

        if (!this.save(user))
            throw new BusinessException(CodeEnum.REGISTER_ERROR);

        return user.getId();
    }

    @Override
    public User login(String userAccount, String userPassword, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(CodeEnum.LOGIN_ERROR, "账户或密码不能为空！");
        }
        if (userAccount.length() < 4 || userPassword.length() < 8) {
            throw new BusinessException(CodeEnum.LOGIN_ERROR, "账户或密码长度不合法！");
        }
        if (!userAccount.matches("^[a-zA-Z0-9]+$")) {
            throw new BusinessException(CodeEnum.LOGIN_ERROR, "账户只能由数字、英文组成！");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("user_password", encrypt(userPassword));
        User user = this.getOne(queryWrapper);
        if (user == null) throw new BusinessException(CodeEnum.LOGIN_ERROR, "账户或密码错误");

        HttpSession session = request.getSession();
        User safetyUser = getSafetyUser(user);
        session.setAttribute(UserConstant.LOGIN_USER_SESSION_KEY, safetyUser);

        return safetyUser;
    }

    @Override
    public User getSafetyUser(User user) {
        User safetyUser = new User();

        safetyUser.setId(user.getId());
        safetyUser.setNickname(user.getNickname());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvatar(user.getAvatar());
        safetyUser.setGender(user.getGender());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setUserRole(user.getUserRole());
        safetyUser.setCreateTime(user.getCreateTime());
        safetyUser.setUpdateTime(user.getUpdateTime());
        safetyUser.setDeleteFlag(user.getDeleteFlag());

        return safetyUser;
    }

    @Override
    public Integer logout(HttpServletRequest request) {
        request.getSession().removeAttribute(UserConstant.LOGIN_USER_SESSION_KEY);
//        request.getSession().invalidate();
        return 1;
    }

    public String encrypt(String userPassword) {
        StringBuilder hexPassword = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(userPassword.getBytes());
            for (byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexPassword.append('0');
                }
                hexPassword.append(hex);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hexPassword.toString();
    }
}