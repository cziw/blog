package com.example.blog.controller;

import com.example.blog.config.UserLoginToken;
import com.example.blog.domain.User;
import com.example.blog.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;


    /**
     * 查询所有用户的信息
     *
     * @return
     */
    @UserLoginToken
    @RequestMapping("queryAllUser")
    public List<User> queryAllUser() {
        return adminService.queryAllUser();
    }
}
