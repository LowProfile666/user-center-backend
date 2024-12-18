package com.zsm.usercenter.service;

import com.zsm.usercenter.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author : ZSM
 * Time :  2024/12/17
 */

@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void testRegister() {
        String userAccount = "lowProfile";
        String userPassword = "12345678";
        String confirmPassword = "12345678";
        Long res = null;

        res = userService.register(userAccount, userPassword, confirmPassword);
        assertEquals(1, res);

        userAccount = "low";
        res = userService.register(userAccount, userPassword, confirmPassword);
        assertEquals(-1, res);
        userAccount = "lowProfile";

        userPassword = "123456";
        res = userService.register(userAccount, userPassword, confirmPassword);
        assertEquals(-1, res);
        userPassword = "12345678";

        userAccount = "123abc*（）+";
        res = userService.register(userAccount, userPassword, confirmPassword);
        assertEquals(-1, res);
        userAccount = "lowProfile";

        res = userService.register(userAccount, userPassword, confirmPassword);
        assertEquals(-1, res);

        confirmPassword = "123456";
        res = userService.register(userAccount, userPassword, confirmPassword);
        assertEquals(-1, res);
    }

    @Test
    public void testSave() {
        List<User> list = userService.list();
        System.out.println(list);
    }
}