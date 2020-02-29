package com.example.blog.service;

import com.example.blog.domain.User;
import org.springframework.stereotype.Service;

/**
 * 用户信息修改
 */
@Service
public interface UserInfoService {
    /**
     * 用户修改昵称
     *
     * @param user
     * @return
     */
    int userNameUpdate(User user);

    /**
     * 用户修改密码
     *
     * @param user
     * @return
     */
    int userPasswordUpdate(User user);

    /**
     * 根据ID查询原来的密码
     *
     * @param user_id
     * @return
     */
    User queryPwdById(int user_id);
}
