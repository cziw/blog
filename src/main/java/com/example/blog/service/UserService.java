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
     * 查询用户手机号是否重复
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

    /**
     * 头像上传
     *
     * @param user
     * @return
     */
    int photoUpload(User user);

    /**
     * 头像下载base64
     *
     * @param user_id
     * @return
     */
    String photoDownload(int user_id);

    /**
     * 用户信息修改
     *
     * @param user
     * @return
     */
    int updateUserInfo(User user);

    /**
     * 根据ID查用户信息
     *
     * @param user_id
     * @return
     */
    User queryUserById(int user_id);
}
