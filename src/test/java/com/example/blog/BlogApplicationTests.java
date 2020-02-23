package com.example.blog;

import com.example.blog.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private UserService userSerivce;
    @Test
    void contextLoads() {

    }


}
