package com.example.blog.mapper;

import com.example.blog.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper {
    /**
     * 获取所有用户列表
     * @return
     */
    @Select("select * from user")
    List<User> getAllUsers();

    @Select("select * from user where uid = 1")
    User getOneName();
}
