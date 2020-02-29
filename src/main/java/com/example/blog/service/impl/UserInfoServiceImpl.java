package com.example.blog.service.impl;

import com.example.blog.domain.User;
import com.example.blog.mapper.UserInfoMapper;
import com.example.blog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired()
    private UserInfoMapper userInfoMapper;

    @Override
    public int userNameUpdate(User user) {
        return userInfoMapper.userNameUpdate(user);
    }

    @Override
    public int userPasswordUpdate(User user) {
        return userInfoMapper.userPasswordUpdate(user);
    }

    @Override
    public User queryPwdById(int user_id) {
        return userInfoMapper.queryPwdById(user_id);
    }


}
