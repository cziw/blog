package com.example.blog.mapper;

import com.example.blog.domain.Mail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface MailMapper {
    /**
     * 邮箱验证码储存到数据库
     *
     * @param mail
     * @return
     */
    @Insert("INSERT into zj_mail_check (email,mailcheckCode,time) VALUES(#{email},#{mailcheckCode},#{time})")
    int getCheckCode(Mail mail);

    /**
     * 检查邮箱验证码
     *
     * @param email
     * @return
     */
    @Select("Select * from zj_mail_check where email = #{email}")
    Mail checkMailCode(String email);

    /**
     * 删除邮箱验证码
     *
     * @param email
     * @return
     */
    @Delete("DELETE from zj_mail_check where email = #{email}")
    int delMailCodeByMail(String email);
}
