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
    public User queryUserEmail(String user_email) {
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

    @Override
    public int photoUpload(User user) {
        return userMapper.photoUpload(user);
    }

    @Override
    public String photoDownload(int user_id) {
        return userMapper.photoDownload(user_id);
    }

    @Override
    public int updateUserInfo(User user) {
        return userMapper.updateUserInfo(user);
    }

    @Override
    public User queryUserById(int user_id) {
        return userMapper.queryUserById(user_id);
    }
}
