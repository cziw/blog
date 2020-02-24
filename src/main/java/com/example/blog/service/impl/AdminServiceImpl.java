package com.example.blog.service.impl;

import com.example.blog.domain.User;
import com.example.blog.mapper.AdminMapper;
import com.example.blog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired()
    private AdminMapper adminMapper;

    @Override
    public List<User> queryAllUser() {
        return adminMapper.queryAllUser();
    }
}
