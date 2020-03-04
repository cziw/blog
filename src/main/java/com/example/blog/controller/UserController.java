package com.example.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.blog.config.UserLoginToken;
import com.example.blog.domain.CommonResult;
import com.example.blog.domain.Mail;
import com.example.blog.domain.User;
import com.example.blog.service.impl.MailServiceImpl;
import com.example.blog.service.impl.TokenServiceImpl;
import com.example.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MailServiceImpl mailService;
    @Autowired
    private TokenServiceImpl tokenService;

    /**
     * 邮箱验证码
     *
     * @param user_email
     * @return
     */
    @RequestMapping("getCheckCode")
    public CommonResult getCheckCode(@RequestParam(name = "user_email") String user_email) {
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "小伙子，你的注册验证码是：" + checkCode;
        try {
            Mail mail = new Mail(user_email, checkCode, Long.toString(new Date().getTime() + 600000));
            // 检查10分钟之内是否已经发送
            Mail mail2 = mailService.checkMailCode(user_email);
            if (!StringUtils.isEmpty(mail2)) {
                // 已经有验证码在数据库里 检查时间是否大于十分钟
                if (Long.parseLong(mail2.getTime()) >= new Date().getTime()) {
                    return new CommonResult(400, "十分钟之内无法再次发送，请检查邮箱邮件");
                }
            }
            if (!StringUtils.isEmpty(userService.queryUserEmail("user_email"))) {
                // 用户已经存在
                return new CommonResult(400, "你已经注册过了，请登录");
            }
            // 先删除数据库里的验证码
            mailService.delMailCodeByMail(user_email);
            // 验证码储存到数据库
            mailService.getCheckCode(mail);
            mailService.sendMail(user_email, "欢迎注册CODW编程世界", message);
            return new CommonResult(200, "发送成功");
        } catch (Exception e) {
            System.out.println(e);
            return new CommonResult(400, "发送失败");
        }
    }

    /**
     * 用户首页注册
     *
     * @return
     */
    @RequestMapping("register")
    public CommonResult userRegister(@RequestParam(name = "user_ip") String user_ip,
                                     @RequestParam(name = "user_name") String user_name,
                                     @RequestParam(name = "user_password") String user_password,
                                     @RequestParam(name = "user_email") String user_email,
                                     @RequestParam(name = "user_telephone_number") String user_telephone_number,
                                     @RequestParam(name = "code") String code) {
        User user = new User(user_ip, user_name, user_password, user_email, user_telephone_number, new Date());
        // 获取数据库里的验证码对象
        Mail mail = mailService.checkMailCode(user_email);
        if (code.equals(mail.getMailcheckCode())) {
            // 验证码相同 下一步确认时间
            if (Long.parseLong(mail.getTime()) >= new Date().getTime()) {
                // 时间确认通过
                if (userService.queryUserName(user_name).size() == 0 && userService.queryUserEmail(user_email).size() == 0 && userService.queryUserTel(user_telephone_number).size() == 0) {
                    if (userService.userRegister(user) == 1) {
                        // 注册成功返回用户对象
                        return new CommonResult(200, user, "注册成功");
                    } else {
                        return new CommonResult(400, "注册失败");
                    }
                } else {
                    // 注册信息中某个字段与他人重复
                    return new CommonResult(400, "请检查昵称");
                }
            } else {
                // 邮箱验证码过期
                return new CommonResult(400, "邮箱验证码过期");
            }
        } else {
            // 邮箱验证码不对
            return new CommonResult(400, "邮箱验证码不对");
        }
    }

    /**
     * 查询用户账号是否重复
     *
     * @param user_name
     * @return
     */
    @RequestMapping("queryUserName")
    public CommonResult queryUserName(@RequestParam(name = "user_name") String user_name) {
        if (userService.queryUserName(user_name).size() == 0) {
            return new CommonResult(200, "昵称可用");
        } else {
            return new CommonResult(400, "昵称已被使用");
        }
    }

    /**
     * 查询用户邮箱是否重复
     *
     * @param user_email
     * @return
     */
    @RequestMapping("queryUserEmail")
    public CommonResult queryUserEmail(@RequestParam(name = "user_email") String user_email) {
        if (userService.queryUserEmail(user_email).size() == 0) {
            return new CommonResult(200, "邮箱可用");
        } else {
            return new CommonResult(400, "邮箱已被使用");
        }
    }

    /**
     * 查询用户手机号是否重复
     *
     * @param user_telephone_number
     * @return
     */
    @RequestMapping("queryUserTel")
    public CommonResult queryUserTel(@RequestParam(name = "user_telephone_number") String user_telephone_number) {
        if (userService.queryUserTel(user_telephone_number).size() == 0) {
            return new CommonResult(200, "手机号可用");
        } else {
            return new CommonResult(400, "手机号已被使用");
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
    public CommonResult userLogin(@RequestParam(name = "user_email") String user_email,
                                  @RequestParam(name = "user_password") String user_password) {
        User user = userService.userLogin(new User(user_email, user_password));
        if (!StringUtils.isEmpty(user)) {
            String token = tokenService.getToken(user);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
            jsonObject.put("user", user);
            // 登录成功返回用户对象
            return new CommonResult(200, jsonObject, "登录成功");
        } else {
            return new CommonResult(400, "账号密码错误");
        }
    }

    /**
     * 头像上传base64
     *
     * @param user_profile_photo
     * @return
     */
    @UserLoginToken
    @RequestMapping("photoUpload")
    public CommonResult photoUpload(@RequestParam(name = "user_profile_photo") String user_profile_photo,
                                    HttpServletRequest httpServletRequest) {
        User user = new User();
        user.setUser_id(Integer.parseInt(JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0)));
        user.setUser_profile_photo(user_profile_photo);
        int i = userService.photoUpload(user);
        if (i == 1) {
            return new CommonResult(200, user_profile_photo, "上传成功");
        } else {
            return new CommonResult(400, "上传成功");
        }
    }

    /**
     * 头像下载base64
     *
     * @param httpServletRequest
     * @return
     */
    @UserLoginToken
    @RequestMapping("photoDownload")
    public CommonResult photoDownload(HttpServletRequest httpServletRequest) {
        String s = userService.photoDownload(Integer.parseInt(JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0)));
        if (s != null) {
            return new CommonResult(200, s, "下载成功");
        } else {
            return new CommonResult(400, "下载失败");
        }
    }

    /**
     * 用户信息修改
     *
     * @param user_id
     * @param user_name
     * @param user_password
     * @param user_email
     * @param user_telephone_number
     * @return
     */
    @UserLoginToken
    @RequestMapping("updateUserInfo")
    public ResponseEntity<?> updateUserInfo(@RequestParam(name = "user_id") int user_id,
                                            @RequestParam(name = "user_name") String user_name,
                                            @RequestParam(name = "user_password") String user_password,
                                            @RequestParam(name = "user_email") String user_email,
                                            @RequestParam(name = "user_telephone_number") String user_telephone_number) {
        User old_user = userService.queryUserById(user_id);
        System.out.println("原来的USER：" + old_user);
        User user = new User();
        user.setUser_id(user_id);
        user.setUser_name(user_name);
        if (user_name.length() == 0) {
            user.setUser_name(old_user.getUser_name());
        }
        user.setUser_password(user_password);
        if (user_password.length() == 0) {
            user.setUser_password(old_user.getUser_password());
        }
        user.setUser_email(user_email);
        if (user_email.length() == 0) {
            user.setUser_email(old_user.getUser_email());
        }
        user.setUser_telephone_number(user_telephone_number);
        if (user_telephone_number.length() == 0) {
            user.setUser_telephone_number(old_user.getUser_telephone_number());
        }
        System.out.println("现在的USER：" + user);
        int i = userService.updateUserInfo(user);
        if (i == 1) {
            CommonResult cr = new CommonResult(200, user, "修改成功");
            return new ResponseEntity<>(cr, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("修改失败", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 根据token查用户信息
     *
     * @param httpServletRequest
     * @return
     */
    @UserLoginToken
    @RequestMapping("queryUserById")
    public CommonResult queryUserById(HttpServletRequest httpServletRequest) {
        User user = userService.queryUserById(Integer.parseInt(JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0)));
        if (!StringUtils.isEmpty(user)) {
            return new CommonResult(200, user, "查找成功");
        } else {
            return new CommonResult(400, "查找失败");
        }
    }
}
