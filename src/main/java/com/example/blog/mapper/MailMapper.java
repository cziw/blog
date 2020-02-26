package com.example.blog.mapper;

import com.example.blog.domain.Mail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MailMapper {
    /**
     * 邮箱验证码储存到数据库
     *
     * @param uid
     * @param mailCheckCode
     * @param time
     * @return
     */
    @Insert("INSERT into mailcheckcode (uid,mailCheckCode,time) VALUES(#{uid},#{mailCheckCode},#{time})")
    int getCheckCode(int uid, String mailCheckCode, String time);

    /**
     * 检查邮箱验证码
     * @param uid
     * @return
     */
    @Select("Select * from mailcheckcode where uid = #{uid}")
    Mail checkMailCode(int uid);
}
