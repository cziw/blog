package com.example.blog.service;

import com.example.blog.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface UserService {
    /**
     * 用户首页注册
     *
     * @param user
     * @return
     */
    int userRegister(User user);

    /**
     * 查询用户账号是否重复
     *
     * @param user_name
     * @return
     */
    List<User> queryUserName(String user_name);

    /**
     * 查询用户邮箱是否重复
     *
     * @param user_email
     * @return
     */
    List<User> queryUserEmail(String user_email);


    /**
     * 查询用户昵称是否重复
     *
     * @param user_telephone_number
     * @return
     */
    List<User> queryUserTel(String user_telephone_number);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    User userLogin(User user);
}
