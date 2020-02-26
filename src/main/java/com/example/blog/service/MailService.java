package com.example.blog.service;

import org.springframework.stereotype.Service;

/**
 * 邮箱验证码接口
 */
@Service
public interface MailService {
    void sendMail(String to, String title, String content);
}
