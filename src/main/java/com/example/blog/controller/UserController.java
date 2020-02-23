package com.example.blog.controller;

import com.example.blog.domain.CommonResult;
import com.example.blog.domain.User;
import com.example.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户首页注册
     *
     * @return
     */
    @RequestMapping("register")
    public ResponseEntity<?> userRegister(@RequestParam(name = "user_ip") String user_ip,
                                          @RequestParam(name = "user_name") String user_name,
                                          @RequestParam(name = "user_password") String user_password,
                                          @RequestParam(name = "user_email") String user_email,
                                          @RequestParam(name = "user_telephone_number") String user_telephone_number) {
        User user = new User(user_ip, user_name, user_password, user_email, user_telephone_number, new Date());
        if (userService.queryUserName(user_name).size() == 0 && userService.queryUserEmail(user_email).size() == 0 && userService.queryUserTel(user_telephone_number).size() == 0) {
            if (userService.userRegister(user) == 1) {
                // 注册成功返回用户对象
                CommonResult cr = new CommonResult(200, user, "注册成功");
                return new ResponseEntity<>(cr, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("注册失败", HttpStatus.NOT_IMPLEMENTED);
            }
        } else {
            // 注册信息中某个字段与他人重复
            return new ResponseEntity<>("注册失败,请检查昵称、邮箱、手机号是否可用", HttpStatus.NOT_IMPLEMENTED);
        }
    }

    /**
     * 查询用户账号是否重复
     *
     * @param user_name
     * @return
     */
    @RequestMapping("queryUserName")
    public ResponseEntity<?> queryUserName(@RequestParam(name = "user_name") String user_name) {
        if (userService.queryUserName(user_name).size() == 0) {
            return new ResponseEntity<>("昵称可用",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("昵称已被使用",HttpStatus.FOUND);
        }
    }

    /**
     * 查询用户邮箱是否重复
     *
     * @param user_email
     * @return
     */
    @RequestMapping("queryUserEmail")
    public ResponseEntity<?> queryUserEmail(@RequestParam(name = "user_email") String user_email) {
        if (userService.queryUserEmail(user_email).size() == 0) {
            return new ResponseEntity<>("邮箱可用", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("邮箱已被使用", HttpStatus.FOUND);
        }
    }

    /**
     * 查询用户手机号是否重复
     *
     * @param user_telephone_number
     * @return
     */
    @RequestMapping("queryUserTel")
    public ResponseEntity<?> queryUserTel(@RequestParam(name = "user_telephone_number") String user_telephone_number) {
        if (userService.queryUserTel(user_telephone_number).size() == 0) {
            return new ResponseEntity<>("手机号可用", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("手机号已被使用", HttpStatus.FOUND);
        }
    }

    /**
     * 用户登录
     *
     * @param user_email
     * @param user_password
     * @return
     */
    @RequestMapping("userLogin")
    public ResponseEntity<?> userLogin(@RequestParam(name = "user_email") String user_email,
                                       @RequestParam(name = "user_password") String user_password) {
        User user = userService.userLogin(new User(user_email, user_password));
        if (!StringUtils.isEmpty(user)) {
            // 登录成功返回用户对象
            CommonResult cr = new CommonResult(200, user, "登录成功");
            return new ResponseEntity<>(cr, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("账号密码错误", HttpStatus.BAD_REQUEST);
        }
    }
}
