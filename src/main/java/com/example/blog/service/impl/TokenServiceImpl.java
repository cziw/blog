package com.example.blog.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.blog.domain.User;
import com.example.blog.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    /**
     * token的生成方法
     *
     * @param user
     * @return
     */
    @Override
    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(String.valueOf(user.getUser_id()))
                .sign(Algorithm.HMAC256(user.getUser_password()));
        return token;
    }
}
