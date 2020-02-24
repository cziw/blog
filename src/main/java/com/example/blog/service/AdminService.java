package com.example.blog.service;

import com.example.blog.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    /**
     * 查询所有用户的信息
     *
     * @return
     */
    List<User> queryAllUser();
}
