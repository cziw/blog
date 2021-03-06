package com.example.blog.service;

import com.example.blog.domain.Mail;
import org.springframework.stereotype.Service;

/**
 * 邮箱验证码接口
 */
@Service
public interface MailService {
    /**
     * 发送纯文本邮件
     * @param to
     * @param title
     * @param content
     */
    void sendMail(String to, String title, String content);

    /**
     * 邮箱验证码储存到数据库
     *
     * @param uid
     * @param mailCheckCode
     * @param time
     * @return
     */
    int getCheckCode(int uid, String mailCheckCode, String time);

    /**
     * 检查邮箱验证码
     *
     * @param uid
     * @return
     */
    Mail checkMailCode(int uid);
}
