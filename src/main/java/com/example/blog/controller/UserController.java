package com.example.blog.controller;

import com.example.blog.domain.User;
import com.example.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("getUserList")
    public List<User> getUserList(){
        return userService.getUserList();
    }

    @RequestMapping("get")
    public String getOneName(){
        return userService.getOneName().getUsername();
    }
}
