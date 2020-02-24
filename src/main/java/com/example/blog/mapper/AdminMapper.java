package com.example.blog.mapper;

import com.example.blog.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AdminMapper {
    /**
     * 查询所有用户的信息
     *
     * @return
     */
    @Select("SELECT * FROM zj_users")
    List<User> queryAllUser();
}
