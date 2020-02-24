package com.example.blog.mapper;

import com.example.blog.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface UserMapper {

    /**
     * 用户首页注册
     *
     * @param user
     * @return
     */
    @Insert("insert into zj_users(user_ip, user_name,user_password,user_email,user_registration_time,user_telephone_number) values (#{user_ip},#{user_name},#{user_password},#{user_email},#{user_registration_time},#{user_telephone_number})")
    int userRegister(User user);

    /**
     * 查询用户账号是否重复
     *
     * @param user_name
     * @return
     */
    @Select("SELECT * FROM zj_users where user_name=#{user_name}")
    List<User> queryUserName(String user_name);

    /**
     * 查询用户邮箱是否重复
     *
     * @param user_email
     * @return
     */
    @Select("SELECT * FROM zj_users where user_email=#{user_email}")
    List<User> queryUserEmail(String user_email);

    /**
     * 查询用户手机号是否重复
     *
     * @param user_telephone_number
     * @return
     */
    @Select("SELECT * FROM zj_users where user_email=#{user_telephone_number}")
    List<User> queryUserTel(String user_telephone_number);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @Select("SELECT * FROM zj_users where user_email=#{user_email} and user_password=#{user_password}")
    User userLogin(User user);

    /**
     * 头像上传base64
     *
     * @param user
     * @return
     */
    @Update("update zj_users set user_profile_photo = #{user_profile_photo} where user_id = #{user_id} ")
    int photoUpload(User user);

    /**
     * 头像下载base64
     *
     * @param user_id
     * @return
     */
    @Select("SELECT user_profile_photo FROM zj_users where user_id = #{user_id}")
    String photoDownload(int user_id);

    /**
     * 用户信息修改
     *
     * @param user
     * @return
     */
    @Update("update zj_users set user_name = '23' , user_password = '34',user_email='df',user_telephone_number='34' where user_id = '28'")
    int updateUserInfo(User user);

    /**
     * 根据ID查用户信息
     *
     * @param user_id
     * @return
     */
    @Select("SELECT * FROM zj_users where user_id = #{user_id}")
    User queryUserById(int user_id);
}
