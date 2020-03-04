package com.example.blog.service;

import com.example.blog.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    /**
     * token的生成方法
     * @param user
     * @return
     */
    String getToken(User user);
}
