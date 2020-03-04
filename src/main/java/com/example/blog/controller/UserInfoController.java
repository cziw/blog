package com.example.blog.controller;

import com.auth0.jwt.JWT;
import com.example.blog.config.UserLoginToken;
import com.example.blog.domain.CommonResult;
import com.example.blog.domain.User;
import com.example.blog.service.impl.UserInfoServiceImpl;
import com.example.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoServiceImpl userInfoService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户修改昵称
     *
     * @return
     */
    @UserLoginToken
    @RequestMapping("userNameUpdate")
    public CommonResult userNameUpdate(HttpServletRequest httpServletRequest,
                                       @RequestParam(name = "user_new_name") String user_new_name) {
        if (userService.queryUserName(user_new_name).size() == 0) {
            // 昵称没有重复
            User user = new User();
            user.setUser_id(Integer.parseInt(JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0)));
            user.setUser_name(user_new_name);
            userInfoService.userNameUpdate(user);
            return new CommonResult(200, "昵称修改成功");
        } else {
            // 昵称重复
            return new CommonResult(400, "昵称重复，请重新修改");
        }
    }

    /**
     * 用户修改密码
     *
     * @return
     */
    @UserLoginToken
    @RequestMapping("userPasswordUpdate")
    public CommonResult userPasswordUpdate(HttpServletRequest httpServletRequest,
                                           @RequestParam(name = "user_old_password") String user_old_password,
                                           @RequestParam(name = "user_new_password") String user_new_password) {
        User old_user = userInfoService.queryPwdById(Integer.parseInt(JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0)));
        if (old_user.getUser_password().equals(user_old_password)) {
            // 原始密码校验通过
            User user = new User();
            user.setUser_id(old_user.getUser_id());
            user.setUser_password(user_new_password);
            userInfoService.userPasswordUpdate(user);
            return new CommonResult(200, "密码修改成功");
        } else {
            // 原始密码错误
            return new CommonResult(0, "原始密码错误");
        }
    }

}
