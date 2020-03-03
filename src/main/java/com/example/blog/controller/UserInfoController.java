package com.example.blog.controller;

import com.example.blog.domain.CommonResult;
import com.example.blog.domain.User;
import com.example.blog.service.impl.UserInfoServiceImpl;
import com.example.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("userNameUpdate")
    public CommonResult userNameUpdate(@RequestParam(name = "user_id") int user_id,
                                       @RequestParam(name = "user_new_name") String user_new_name) {
        if (userService.queryUserName(user_new_name).size() == 0) {
            // 昵称没有重复
            User user = new User();
            user.setUser_id(user_id);
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
    @RequestMapping("userPasswordUpdate")
    public CommonResult userPasswordUpdate(@RequestParam(name = "user_id") int user_id,
                                           @RequestParam(name = "user_old_password") String user_old_password,
                                           @RequestParam(name = "user_new_password") String user_new_password) {
        User old_user = userInfoService.queryPwdById(user_id);
        if (old_user.getUser_password().equals(user_old_password)) {
            // 原始密码校验通过
            User user = new User();
            user.setUser_id(user_id);
            user.setUser_password(user_new_password);
            userInfoService.userPasswordUpdate(user);
            return new CommonResult(200, "密码修改成功");
        } else {
            // 原始密码错误
            return new CommonResult(0, "原始密码错误");
        }
    }

}
