package com.example.blog.mapper;

import com.example.blog.domain.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserInfoMapper {

    /**
     * 用户修改昵称
     *
     * @param user
     * @return
     */
    @Update("update zj_users set user_name=#{user_name} where user_id = #{user_id}")
    int userNameUpdate(User user);

    /**
     * 用户修改密码
     *
     * @param user
     * @return
     */
    @Update("update zj_users set user_password = #{user_password} where user_id = #{user_id}")
    int userPasswordUpdate(User user);

    /**
     * 根据ID查询原来的密码
     *
     * @param user_id
     * @return
     */
    @Select("SELECT user_password FROM zj_users where user_id = #{user_id}")
    User queryPwdById(int user_id);


}
