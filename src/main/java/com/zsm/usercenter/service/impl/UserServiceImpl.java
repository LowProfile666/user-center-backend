package com.zsm.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsm.usercenter.constant.UserConstant;
import com.zsm.usercenter.model.User;
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
        if (StringUtils.isAllBlank(userAccount, userPassword, confirmPassword)) {
            return -1L;
        }
        if (userAccount.length() < 4 || userPassword.length() < 8 || confirmPassword.length() < 8) {
            return -1L;
        }
        if (!userAccount.matches("^[a-zA-Z0-9]+$")) {
            return -1L;
        }
        if (!userPassword.equals(confirmPassword)) {
            return -1L;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        if (this.getOne(queryWrapper) != null) {
            return -1L;
        }

        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encrypt(userPassword));

        return this.save(user) ? user.getId() : -1L;
    }

    @Override
    public User login(String userAccount, String userPassword, HttpServletRequest request) {
        if (StringUtils.isAllBlank(userAccount, userPassword)) {
            return null;
        }
        if (userAccount.length() < 4 || userPassword.length() < 8) {
            return null;
        }
        if (!userAccount.matches("^[a-zA-Z0-9]+$")) {
            return null;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("user_password", encrypt(userPassword));
        User user = this.getOne(queryWrapper);
        if (user == null) return null;

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




