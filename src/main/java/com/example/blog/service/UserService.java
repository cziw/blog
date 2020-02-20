package com.example.blog.service;

import com.example.blog.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface UserService {
    /**
     * 获取所有用户列表
     * @return
     */
    List<User> getUserList();

    /**
     * 获取第一个用户的名字
     * @return
     */
    User getOneName();

}
