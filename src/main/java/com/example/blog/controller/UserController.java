package com.example.blog.controller;

import com.example.blog.domain.CommonResult;
import com.example.blog.domain.Mail;
import com.example.blog.domain.User;
import com.example.blog.service.impl.MailServiceImpl;
import com.example.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MailServiceImpl mailService;

    @RequestMapping(value = "/CheckMailCode", method = RequestMethod.GET)
    public int CheckMailCode(@RequestParam(name = "code") String code,
                             @RequestParam(name = "user_id") int user_id) {
        // 获取数据库里的验证码对象
        Mail mail = mailService.checkMailCode(user_id);

        if (mail.getMailcheckCode().equals(code)) {
            // 验证码相同 下一步确认时间
            if (Long.parseLong(mail.getTime()) >= new Date().getTime()) {
                // 时间确认通过
            }
        } else {
            // 验证码不对
        }
        return 1;
    }

    /**
     * 邮箱验证码
     *
     * @param user_email
     * @return
     */
    @RequestMapping(value = "/getCheckCode", method = RequestMethod.POST)
    public int getCheckCode(@RequestParam(name = "user_email") String user_email,
                            @RequestParam(name = "user_id") int user_id) {
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        String message = "小伙子，你的注册验证码是：" + checkCode;
        try {
            mailService.getCheckCode(user_id, checkCode, Long.toString(new Date().getTime() + 600000));
            mailService.sendMail(user_email, "注册验证码", message);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

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
                                          @RequestParam(name = "user_telephone_number") String user_telephone_number,
                                          @RequestParam(name = "uid") int uid) {
        User user = new User(user_ip, user_name, user_password, user_email, user_telephone_number, new Date());
        // 获取数据库里的验证码对象 uid是游客id
        Mail mail = mailService.checkMailCode(uid);
        if (mail.getMailcheckCode().equals("code")) {
            // 验证码相同 下一步确认时间
            if (Long.parseLong(mail.getTime()) >= new Date().getTime()) {
                // 时间确认通过
            }
        } else {
            // 验证码不对
        }
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
    public CommonResult queryUserName(@RequestParam(name = "user_name") String user_name) {
        if (userService.queryUserName(user_name).size() == 0) {
            return new CommonResult(1, null,"昵称可用");
        } else {
            return new CommonResult(0,null,"昵称已被使用" );
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
            return new CommonResult(1, null,"邮箱可用");
        } else {
            return new CommonResult(0, null,"邮箱已被使用");
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
            return new CommonResult(1,null,"手机号可用");
        } else {
            return new CommonResult(0,null,"手机号已被使用");
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

    /**
     * 头像上传base64
     *
     * @param user_profile_photo
     * @return
     */
    @RequestMapping("photoUpload")
    public ResponseEntity<?> photoUpload(@RequestParam(name = "user_profile_photo") String user_profile_photo,
                                         @RequestParam(name = "user_id") int user_id) {
        User user = new User();
        user.setUser_id(user_id);
        user.setUser_profile_photo(user_profile_photo);
        int i = userService.photoUpload(user);
        if (i == 1) {
            return new ResponseEntity<>(user_profile_photo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 头像下载base64
     *
     * @param user_id
     * @return
     */
    @RequestMapping("photoDownload")
    public ResponseEntity<?> photoDownload(@RequestParam(name = "user_id") int user_id) {
        String s = userService.photoDownload(user_id);
        if (s != null) {
            return new ResponseEntity<>(s, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("头像获取失败或者没有上传头像", HttpStatus.BAD_REQUEST);
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
     * 根据ID查用户信息
     *
     * @param user_id
     * @return
     */
    @RequestMapping("queryUserById")
    public ResponseEntity<?> queryUserById(@RequestParam(name = "user_id") int user_id) {
        User user = userService.queryUserById(user_id);
        if (!StringUtils.isEmpty(user)) {
            CommonResult cr = new CommonResult(200, user, "查找成功");
            return new ResponseEntity<>(cr, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("查找失败", HttpStatus.BAD_REQUEST);
        }
    }
}
