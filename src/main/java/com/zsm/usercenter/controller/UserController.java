package com.zsm.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsm.usercenter.constant.UserConstant;
import com.zsm.usercenter.model.User;
import com.zsm.usercenter.model.request.UserLoginRequest;
import com.zsm.usercenter.model.request.UserRegisterRequest;
import com.zsm.usercenter.model.response.R;
import com.zsm.usercenter.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : ZSM
 * Time :  2024/12/18
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public R<User> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) return R.error("请求体为空！");
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) return R.error("账户或密码不能为空！");

        return R.success(userService.login(userAccount, userPassword, request));
    }

    @PostMapping("/register")
    public R<Long> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) return R.error("请求体为空！");
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String confirmPassword = userRegisterRequest.getConfirmPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, confirmPassword)) return R.error("账户或密码不能为空！");

        return R.success(userService.register(userAccount, userPassword, confirmPassword));
    }

    @GetMapping("/search")
    public R<List<User>> search(String nickname, HttpServletRequest request) {
        if (StringUtils.isEmpty(nickname)) return R.error("昵称不能为空！");
        if (!isAdmin(request)) return R.error("当前用户不是管理员用户！");

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("nickname", nickname);
        List<User> userList = userService.list(queryWrapper);

        userList = userList.stream().map((user) -> userService.getSafetyUser(user)).toList();

        return R.success(userList);
    }

    @PostMapping("/delete")
    public R<Boolean> delete(@RequestBody Long id, HttpServletRequest request) {
        if (id == null) return R.error("id不能为空！");
        if (!isAdmin(request)) return R.error("当前用户不是管理员用户！");;

        return R.success(userService.removeById(id));
    }

    @GetMapping("/get_current_user")
    public R<User> getCurrentUser(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute(UserConstant.LOGIN_USER_SESSION_KEY);
        if (loginUser == null) return R.error("当前没用登录！");
        User user = userService.getById(loginUser.getId());
        return R.success(userService.getSafetyUser(user));
    }

    @PostMapping("/logout")
    public R<Integer> logout(HttpServletRequest request) {
        return R.success(userService.logout(request));
    }

    private Boolean isAdmin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConstant.LOGIN_USER_SESSION_KEY);
        if (user == null) return false;

        return UserConstant.ADMIN_ROLE.equals(user.getUserRole());
    }
}
