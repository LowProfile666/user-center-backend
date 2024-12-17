package com.zsm.usercenter.service;

import com.zsm.usercenter.model.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
    public void testSave() {
        User user = new User();
    }
}