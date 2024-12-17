package com.zsm.usercenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsm.usercenter.model.User;
import com.zsm.usercenter.service.UserService;
import com.zsm.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 20620
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-12-17 21:20:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




