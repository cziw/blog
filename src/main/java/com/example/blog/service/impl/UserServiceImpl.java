package com.example.blog.service.impl;

import com.example.blog.domain.User;
import com.example.blog.mapper.UserMapper;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired()
    private UserMapper userMapper;

    @Override
    public int userRegister(User user) {
        return userMapper.userRegister(user);
    }

    @Override
    public List<User> queryUserName(String user_name) {
        return userMapper.queryUserName(user_name);
    }

    @Override
    public List<User> queryUserEmail(String user_email) {
        return userMapper.queryUserEmail(user_email);
    }


    @Override
    public List<User> queryUserTel(String user_telephone_number) {
        return userMapper.queryUserTel(user_telephone_number);
    }

    @Override
    public User userLogin(User user) {
        return userMapper.userLogin(user);
    }
}
