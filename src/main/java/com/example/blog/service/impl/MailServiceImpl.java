package com.example.blog.service.impl;

import com.example.blog.domain.Mail;
import com.example.blog.mapper.MailMapper;
import com.example.blog.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 邮箱验证码接口实现
 */
@Service
public class MailServiceImpl implements MailService {
    @Autowired()
    private MailMapper mailMapper;
    @Resource
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;


    /**
     * 发送纯文本邮件.
     *
     * @param to      目标email 地址
     * @param subject 邮件主题
     * @param text    纯文本内容
     */
    @Override
    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    /**
     * 邮箱验证码储存到数据库
     *
     * @param uid
     * @param mailCheckCode
     * @param time
     * @return
     */
    public int getCheckCode(int uid, String mailCheckCode, String time) {
        return mailMapper.getCheckCode(uid, mailCheckCode, time);
    }

    /**
     * 检查邮箱验证码
     *
     * @param uid
     * @return
     */
    @Override
    public Mail checkMailCode(int uid) {
        return mailMapper.checkMailCode(uid);
    }

}
